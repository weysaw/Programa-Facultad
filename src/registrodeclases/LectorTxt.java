package registrodeclases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import java.sql.Time;
import conexion.*;

/**
 * Dada una carpeta, recupera
 *
 * @author Raymundo Perez
 * @version 05.12.2020
 */
public class LectorTxt {

    private final ArrayList<CursoHorario> clases;
    private File carpeta;

    //Constructor de la clase
    public LectorTxt() {
        carpeta = null;
        clases = new ArrayList();
    }

    /**
     * Metodo principal de la clase, permite seleccionar una carpeta con .txt
     * con cursos y los procesa a objetos dentro del programa
     *
     * @return ArrayList con toda la informacion de los cursos
     */
    public ArrayList<CursoHorario> recuperarDatos() {
        clases.clear();//resetea el arrayList
        carpeta = abrirCarpeta();//abre la carpeta donde se ubican los txt
        if (carpeta != null) {
            for (final File archivo : carpeta.listFiles()) { // foreach para cada elemento dentro de la carpeta
                if (Pattern.matches("^\\d{5} .*\\.txt$", archivo.getName())) { //Si cumple con la forma "##### XXXXXX.txt"
                    System.out.println("Leyendo: " + archivo.getName());
                    lecturaIndividual(archivo);
                }
            }
            recuperarNumeroEmpleado();
        }
        return clases;
    }

    /**
     * Si se encuentra disponible el archivo con nombres y numeros de empleado,
     * los agrega al objeto
     */
    private void recuperarNumeroEmpleado() {
        ArrayList<Profesor> profesores = getProfesor();
        if (carpeta != null) {
            for (final File archivo : carpeta.listFiles()) { // foreach para cada elemento dentro de la carpeta
                if (Pattern.matches(".*[Mm][Aa][Ee][Ss][Tt][Rr][Oo].*", archivo.getName())) { //Si contiene la palabra "maestro"
                    System.out.println("Leyendo: " + archivo.getName());
                    BufferedReader reader;
                    try { //lectura del archivo con maestros
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo.getPath()), "UTF-16"));
                        String line;
                        String[] empleado;//numero,nombre
                        //Lectura linea por linea
                        while ((line = reader.readLine()) != null) {
                            if (Pattern.matches("^\\d{6}.*", line)) {
                                empleado = line.split("\t");
                                //compara si existe un maestro con el nombre en la linea
                                for (Profesor profesor : profesores) {
                                    if (profesor.getNom().equals(empleado[1])) {
                                        profesor.setNumEmpleado(empleado[0]);
                                        break;
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                }
            }
        }
    }

    /**
     * Despues de recuperar los datos de los txt, permite obtener los cursos
     *
     * @return ArrayList con cursos
     */
    public ArrayList<Curso> getCurso() {
        ArrayList<Curso> cursos = new ArrayList();
        for (CursoHorario curso : clases) {
            if (!cursos.contains(curso.getCurso())) {
                cursos.add(curso.getCurso());
            }
        }
        return cursos;
    }

    /**
     * Despues de recuperar los datos de los txt, permite obtener las materias
     *
     * @return ArrayList con materias
     */
    public ArrayList<Materia> getMateria() {
        ArrayList<Materia> materias = new ArrayList();
        for (Curso curso : getCurso()) {
            if (!materias.contains(curso.getMateria())) {
                materias.add(curso.getMateria());
            }
        }
        return materias;
    }
    
    /**
     * Despues de recuperar los datos de los txt, permite obtener los horarios
     *
     * @return ArrayList con horarios
     */
    public ArrayList<Horario> getHorario() {
        ArrayList<Horario> horarios = new ArrayList();
        for (CursoHorario curso : clases) {
            if (!horarios.contains(curso.getHorario())) {
                horarios.add(curso.getHorario());
            }
        }
        return horarios;
    }

    /**
     * Despues de recuperar los datos de los txt, permite obtener los maestros
     *
     * @return ArrayList con profesores
     */
    public ArrayList<Profesor> getProfesor() {
        ArrayList<Profesor> profesores = new ArrayList();
        for (Curso curso : getCurso()) {
            if (!profesores.contains(curso.getProfesor())) {
                profesores.add(curso.getProfesor());
            }
        }
        return profesores;
    }

    /**
     * Despues de recuperar los datos de los txt, permite obtener los
     * CursoHorario
     *
     * @return ArrayList con cursosHorario
     */
    public ArrayList<CursoHorario> getCursoHorario() {
        return clases;
    }

    /**
     * Valida si todos los archivos coinciden al mismo semestre, si encuentra
     * uno distinto devuelve false ES NECESARIO HABER UTILIZADO recuperarDatos()
     * ANTES DE USARSE
     *
     * @return true si coinciden los semestres
     */
    public String validarSemestre() {
        String semestre = null;
        int n = 0;//contador para saber el numero de iteraciones
        boolean valido = true;
        if (carpeta != null) {
            for (final File archivo : carpeta.listFiles()) { // foreach para cada elemento dentro de la carpeta
                if (Pattern.matches("^\\d+.+\\.txt$", archivo.getName())) { //Si cumple con la forma "##### XXXXXX XXXXX.txt"
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo.getPath()), "UTF-16"));
                        String line;
                        while ((line = reader.readLine()) != null) {//Empieza la lectura del archivo
                            if (Pattern.matches("^SEMESTRE.*", line)) {//Si es la linea que contiene el semestre
                                if (n == 0) {//Si es el primer elemento lo almacena
                                    semestre = line.split(" ", 2)[1];
                                } else if (!semestre.equals(line.split(" ", 2)[1])) {//si no es el primero, verifica si los siguentes archivos son del mismo semestre que el primero
                                    valido = false;
                                }
                                n++;
                                break;//si ya obtuvo el semestre deja de leer el archivo y pasa al siguiente
                            }
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return (valido) ? semestre + "" : "0";
        } else {
            System.out.println("No se han seleccionado datos");
            return "0";
        }
    }

    /**
     * Se procesa cada archivo linea por linea y agrega los datos de cada curso
     * de ese archivo al arraylist que devuelve la funcion principal
     *
     * @param archivo archivo.txt a procesar
     */
    private void lecturaIndividual(File archivo) {
        int clave;
        String materia = null;
        String semestre;
        String grupo;
        String tipo;
        int hrsTC;
        int hrsAsig;
        String maestro;

        int seccion = 0; //encabezado=0,clases=1,INTERMEDIO= 2 , laboratorio=3
        clave = Integer.parseInt(archivo.getName().split(" ")[0]); //Recupera clave materia del nombre
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo.getPath()), "UTF-16"));
            String line;
            while ((line = reader.readLine()) != null) {
                //Empieza lectura linea por linea del archivo
                switch (seccion) {
                    case 0://encabezado
                        if (Pattern.matches("^MATERIA:.*", line)) {//Si es la linea que contiene la materia
                            materia = line.split(" ", 2)[1];
                        } else if (Pattern.matches("^SEMESTRE.*", line)) {//Si es la linea que contiene el semestre
                            semestre = line.split(" ", 2)[1];
                        } else if (Pattern.matches("^MAESTRO.*", line))//Si es la ultima linea del encabezado, pasa a la siguiente seccion
                        {
                            seccion = 1;
                        }
                        break;
                    case 1://clase
                        if (!Pattern.matches(".*TOTAL DE HRS.*", line)) {//Si la linea no es "TOTAL DE HRS", es una clase
                            String[] sublinea = line.split("\t");
                            maestro = sublinea[0]; //El primer campo es el nombre del maestro
                            if (Pattern.matches(".*T.*", sublinea[2])) {//Si el campo de grupo contiene una T
                                tipo = "TALLER";
                                grupo = sublinea[2].substring(0, 3);//Se toman los primeros 3 caracteres como el grupo 
                            } else {
                                tipo = "CLASE";
                                grupo = sublinea[2];//si no es taller, el grupo se toma tal cual
                            }
                            hrsTC = Integer.parseInt(sublinea[3]);
                            hrsAsig = Integer.parseInt(sublinea[4]);

                            Curso curso = new Curso(asignarProfesor(maestro), new Materia(clave, materia), grupo, tipo, hrsTC, hrsAsig); //Se crea el curso con los datos que ya se tienen

                            //procesado de horas
                            //para separar los dias de las horas, se necesita saber en que caracter inician las horas
                            String[] horas;
                            int n = 0;
                            int m;
                            while (!Pattern.matches("\\d", sublinea[1].charAt(n) + "")) {//mientras que el caracter n sea no numerico
                                n++;
                            }//al final del ciclo se sabe que en el caracter n empiezan las horas
                            if (sublinea[1].length() > 20) {//a veces se repite dos veces el mimso horario, para evitar esto se detecta si la linea es anormalmente larga
                                m = n;
                                while (' ' != sublinea[1].charAt(m)) {//si anormalmente larga, se busca donde termina la primera hora buscando el espacio que las separa
                                    m++;
                                }//al final del ciclo se sabe que en el caracter m esta el espacio
                                horas = sublinea[1].substring(n, m - 1).split("-");//Se obtiene un string[] solo con las horas
                            } else {
                                horas = sublinea[1].substring(n, sublinea[1].length() - 1).split("-");//Se obtiene un string[] solo con las horas
                            }

                            //Por cada una de las siguientes condiciones que se cumpla, se añade un elemento al array
                            if (Pattern.matches(".*L.*", sublinea[1])) {//Si contiene la L de lunes
                                clases.add(new CursoHorario(curso, asignarHorario("LUNES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*Ma.*", sublinea[1])) {//Si contiene Ma de martes
                                clases.add(new CursoHorario(curso, asignarHorario("MARTES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*Mi.*", sublinea[1])) {//Si contiene Mi de miercoles
                                clases.add(new CursoHorario(curso, asignarHorario("MIERCOLES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*J.*", sublinea[1])) {//Si contiene J de jueves
                                clases.add(new CursoHorario(curso, asignarHorario("JUEVES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*V.*", sublinea[1])) {//Si contiene V de viernes
                                clases.add(new CursoHorario(curso, asignarHorario("VIERNES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*S.*", sublinea[1])) {//Si contiene S de sabado
                                clases.add(new CursoHorario(curso, asignarHorario("SABADO",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }

                        } else {
                            seccion = 2;
                        }
                        break;
                    case 2://intermedio
                        if (Pattern.matches(".*LAB.*", line)) {//se identidfica cuando empiezan los laboratorios
                            seccion = 3;
                        }
                        break;
                    case 3://laboratorios
                        if (!Pattern.matches(".*TOTAL DE HRS.*", line)) {//Si la linea no es "TOTAL DE HRS", es una clase
                            String[] sublinea = line.split("\t");
                            maestro = sublinea[0]; //El primer campo es el nombre del maestro
                            tipo = "LABORATORIO";
                            grupo = sublinea[2];//Se toman los primeros 3 caracteres como el grupo                             
                            hrsTC = Integer.parseInt(sublinea[3]);
                            hrsAsig = Integer.parseInt(sublinea[4]);

                            Curso curso = new Curso(asignarProfesor(maestro), new Materia(clave, materia), grupo, tipo, hrsTC, hrsAsig); //Se crea el curso con los datos que ya se tienen

                            //procesado de horas
                            //para separar los dias de las horas, se necesita saber en que caracter inician las horas
                            String[] horas;
                            int n = 0;
                            int m;
                            while (!Pattern.matches("\\d", sublinea[1].charAt(n) + "")) {//mientras que el caracter n sea no numerico
                                n++;
                            }//al final del ciclo se sabe que en el caracter n empiezan las horas
                            if (sublinea[1].length() > 20) {//a veces se repite dos veces el mimso horario, para evitar esto se detecta si la linea es anormalmente larga
                                m = n;
                                while (' ' != sublinea[1].charAt(m)) {//si anormalmente larga, se busca donde termina la primera hora buscando el espacio que las separa
                                    m++;
                                }//al final del ciclo se sabe que en el caracter m esta el espacio
                                horas = sublinea[1].substring(n, m - 1).split("-");//Se obtiene un string[] solo con las horas
                            } else {
                                horas = sublinea[1].substring(n, sublinea[1].length() - 1).split("-");//Se obtiene un string[] solo con las horas
                            }

                            //Por cada una de las siguientes condiciones que se cumpla, se añade un elemento al array
                            if (Pattern.matches(".*L.*", sublinea[1])) {//Si contiene la L de lunes
                                clases.add(new CursoHorario(curso, asignarHorario("LUNES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*Ma.*", sublinea[1])) {//Si contiene Ma de martes
                                clases.add(new CursoHorario(curso, asignarHorario("MARTES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*Mi.*", sublinea[1])) {//Si contiene Mi de miercoles
                                clases.add(new CursoHorario(curso, asignarHorario("MIERCOLES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*J.*", sublinea[1])) {//Si contiene J de jueves
                                clases.add(new CursoHorario(curso, asignarHorario("JUEVES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*V.*", sublinea[1])) {//Si contiene V de viernes
                                clases.add(new CursoHorario(curso, asignarHorario("VIERNES",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }
                            if (Pattern.matches(".*S.*", sublinea[1])) {//Si contiene S de sabado
                                clases.add(new CursoHorario(curso, asignarHorario("SABADO",
                                        new Time(Integer.parseInt(horas[0].split(":")[0]), Integer.parseInt(horas[0].split(":")[1]), 0), //Parece mucho rollo, pero solo se separa el horario en inicio, fin, horas y minutos
                                        new Time(Integer.parseInt(horas[1].split(":")[0]), Integer.parseInt(horas[1].split(":")[1]), 0))));
                            }

                        } else {
                            seccion = 4;
                        }
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica si ya existe un profesor con ese nombre registrado, de ser asi
     * devuelve el objeto correspondiente, en caso contrario crea un nuevo
     * objeto
     *
     * @param profesor nombre del profesor
     * @return objeto correspondiente a ese profesor
     */
    private Profesor asignarProfesor(String nombre) {
        for (Profesor profesor : getProfesor()) {
            if (profesor.getNom().equals(nombre)) {
                return profesor;
            }
        }
        return new Profesor(null,nombre, false);
    }
    
    /**
     * Verifica si ya existe un horario con el mismo dia y horas, de ser asi
     * devuelve el objeto correspondiente, en caso contrario crea un nuevo
     * objeto
     * 
     * @param dia dia correspondiente
     * @param inicio hora de inicio
     * @param fin hora fin
     * @return objeto correspondiente al horario
     */
    private Horario asignarHorario(String dia,Time inicio, Time fin){
        for (Horario horario : getHorario()) {
            if (horario.getDia().equals(dia) && 
                    horario.getHrInicio().getHours()==inicio.getHours() && 
                    horario.getHrInicio().getMinutes()==inicio.getMinutes() &&
                    horario.getHrFin().getHours()==fin.getHours() && 
                    horario.getHrFin().getMinutes()==fin.getMinutes()){
                return horario;
            }
        }
        return new Horario(0, dia, null, inicio, fin);
    }

    /**
     * Abre un file Chooser para seleccionar carpetas
     *
     * @return carpeta seleccionada por el usuario
     */
    private File abrirCarpeta() {
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Seleccione la carpeta donde se ubiquen los archivos");
        f.setAcceptAllFileFilterUsed(false);
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showDialog(null, "Seleccionar");

        return f.getSelectedFile();
    }
}
