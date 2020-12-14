package programafacultad;

import conexion.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Time;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Component;

/**
 * Da de alta los cursos y los usuarios
 *
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo, Perez Valdez Raymundo
 * @version 11.12.2020
 */
public class AltaCursoHorario extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<Materia> materiaDAO;
    private ArrayList<Profesor> profeDAO;
    private final ArrayList<JCheckBox> checkDias;
    private final ArrayList<JComboBox> fines;
    private final ArrayList<JComboBox> inicios;
    private Profesor docente;
    private Materia materia;

    /**
     * Constructor de la clase
     */
    public AltaCursoHorario(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        //Inicializa las relaciones de los checkbox y combobox
        materiaDAO = new ArrayList();
        profeDAO = new ArrayList();
        checkDias = new ArrayList();
        inicios = new ArrayList();
        fines = new ArrayList();
        //Agrega las referencias de los checkbox y combobox
        inicios.add(inicioLunes);
        inicios.add(inicioMartes);
        inicios.add(inicioMiercoles);
        inicios.add(inicioJueves);
        inicios.add(inicioViernes);
        inicios.add(inicioSabado);
        fines.add(finLunes);
        fines.add(finMartes);
        fines.add(finMiercoles);
        fines.add(finJueves);
        fines.add(finViernes);
        fines.add(finSabado);
        checkDias.add(lunes);
        checkDias.add(martes);
        checkDias.add(miercoles);
        checkDias.add(jueves);
        checkDias.add(viernes);
        checkDias.add(sabado);
        bloqueo();
        //Es el mensaje de espera
        MensajeEspera mensaje = new MensajeEspera(principal) {
            @Override
            public void accion(Component cmp) {
                informacion();
            }
        };
        //Muestra el mensaje de espera
        mensaje.mostrarMensaje();
    }

    /**
     * Método para bloquear los comboBox de las horas
     */
    private void bloqueo() {
        inicioLunes.setEnabled(false);
        inicioMartes.setEnabled(false);
        inicioMiercoles.setEnabled(false);
        inicioJueves.setEnabled(false);
        inicioViernes.setEnabled(false);
        inicioSabado.setEnabled(false);
        finLunes.setEnabled(false);
        finMartes.setEnabled(false);
        finMiercoles.setEnabled(false);
        finJueves.setEnabled(false);
        finViernes.setEnabled(false);
        finSabado.setEnabled(false);
    }

    /* Método que trae la información de las materias y docentes para
       insertarla en los comboBox correspondientes*/
    private void informacion() {
        MateriaDAO materiaConexion = new MateriaDAO();
        ProfesorDAO profesor = new ProfesorDAO();
        //Se abren las conexiones
        materiaConexion.abrirSSH();
        materiaConexion.abrirConexion();
        try {
            materiaDAO = materiaConexion.readAll();
            for (Materia materia1 : materiaDAO) {
                materias.addItem(materia1.getClaveMateria() + " " + materia1.getNom());
            }
            profesor.setConexion(materiaConexion.getConexionBD());
            try {
                profeDAO = profesor.readAll();
                for (Profesor profesor1 : profeDAO) {
                    docentes.addItem(profesor1.getNumEmpleado() + " " + profesor1.getNom());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERROR LECTURA PROFESOR\n" + ex.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                cerrrarVentana();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR LECTURA MATERIA\n" + ex.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            cerrrarVentana();
        } finally { // Cierra la conexión SSH
            materiaConexion.cerrarSSH();
        }
    }

    /**
     * Agrega el curso horario indicado por los parametros indicados
     * 
     * @param cursos Agrega el curso
     * @param hrInicioDia Agrega la hr inicio dia
     * @param hrFinDia Agrega la hr fin del día 
     * @param dia Agrega el día
     */
    private void agregarCursoHorario(Curso cursos, JComboBox<String> hrInicioDia, JComboBox<String> hrFinDia, JCheckBox dia) {
        //Convierte las horas del dia
        String hrFin = hrFinDia.getSelectedItem().toString().split(":")[0];
        String hrInicio = hrInicioDia.getSelectedItem().toString().split(":")[0];
        int horaFin = Integer.parseInt(hrFin);
        int horaInicio = Integer.parseInt(hrInicio);

        //Crea las horas con time
        Time tiempoInicio = new Time(horaInicio, 0, 0);
        Time tiempoFin = new Time(horaFin, 0, 0);
        String diaTexto = dia.getText().toUpperCase();
        //Inicializa la hora 
        Horario hora = new Horario(diaTexto, tiempoInicio, tiempoFin);

        //Crea la conexion
        HorarioDAO horarioDAO = new HorarioDAO();
        //Abre la conexion con la BD
        horarioDAO.abrirConexion();
        try {
            //Lee los horarios y busca si tiene repetidos
            Horario repetido = horarioDAO.read(hora);
            //Si no esta repetido la agrega
            if (repetido == null) {
                horarioDAO.append(hora);
                System.out.println("SE REGISTRO EL HORARIO");
                hora = horarioDAO.read(hora);
            } else {
                //Si se repite lo asigna
                hora = repetido;
            }
            //Inicializa el curso horario
            CursoHorario cursoH = new CursoHorario(cursos, hora);
            CursoHorarioDAO cursoHDAO = new CursoHorarioDAO();
            //Pone la conexión al Curso Horario
            cursoHDAO.setConexion(horarioDAO.getConexionBD());
            
            try {
                //Agrega el curso horario
                cursoHDAO.append(cursoH);
                System.out.println("SE REGISTRO EL CURSO HORARIO");
            } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
                JOptionPane.showMessageDialog(this, "Ya existe un curso horario registrado \n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) { //Error en general
                JOptionPane.showMessageDialog(this, "ERROR \n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) { //Error en general
            JOptionPane.showMessageDialog(this, "ERROR\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Asigna el horario dependiendo de los check seleccionados
     * 
     * @param cursos Es el curso agrega
     */
    private void asignarHorario(Curso cursos) {
        // Agregamos los horarios junto con el curso horario a la base de datos por dias
        for (int i = 0; i < 6; i++) {
            if (checkDias.get(i).isSelected()) {
                agregarCursoHorario(cursos, inicios.get(i), fines.get(i), checkDias.get(i));
            }
        }
    }

    /**
     * Valida si el horario no se ha traslapado
     * 
     * @param dia Es el dia que se verifica
     * @param numEmpleado Es el numEmpleado a que se verifica
     * @return Devuelve true si no esta en la lista y false no no esta
     */
    private boolean validarTraslape(String dia, String hrInicio, String hrFin) {
        CursoHorarioDAO dao = new CursoHorarioDAO();
        dao.abrirConexion();
        ArrayList<CursoHorario> curso = null;
        try {
            //Busca el horario en la base de datos con el Queary indicado
            curso = dao.readHrIntervaloProfe(dia, hrInicio, hrFin, docente.getNumEmpleado());
            for (CursoHorario cursoHorario : curso) {
                System.out.println(cursoHorario.getHorario());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        if (curso.isEmpty()) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Alta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        docentes = new javax.swing.JComboBox<>();
        materias = new javax.swing.JComboBox<>();
        tipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        grupo = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        horario = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lunes = new javax.swing.JCheckBox();
        inicioLunes = new javax.swing.JComboBox<>();
        finLunes = new javax.swing.JComboBox<>();
        martes = new javax.swing.JCheckBox();
        inicioMartes = new javax.swing.JComboBox<>();
        finMartes = new javax.swing.JComboBox<>();
        miercoles = new javax.swing.JCheckBox();
        inicioMiercoles = new javax.swing.JComboBox<>();
        finMiercoles = new javax.swing.JComboBox<>();
        jueves = new javax.swing.JCheckBox();
        inicioJueves = new javax.swing.JComboBox<>();
        finJueves = new javax.swing.JComboBox<>();
        viernes = new javax.swing.JCheckBox();
        inicioViernes = new javax.swing.JComboBox<>();
        finViernes = new javax.swing.JComboBox<>();
        sabado = new javax.swing.JCheckBox();
        inicioSabado = new javax.swing.JComboBox<>();
        finSabado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alta de cursos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Facultad de Ingeniería Campus Mexicali");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficos/uabc.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Planta Académica FIM");

        javax.swing.GroupLayout TituloLayout = new javax.swing.GroupLayout(Titulo);
        Titulo.setLayout(TituloLayout);
        TituloLayout.setHorizontalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TituloLayout.setVerticalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TituloLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Alta de Cursos");

        jLabel3.setText("Docente:");

        jLabel4.setText("Materia:");

        jLabel5.setText("Tipo:");

        docentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docentesActionPerformed(evt);
            }
        });

        materias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiasActionPerformed(evt);
            }
        });

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clase", "Taller", "Laboratorio" }));

        jLabel2.setText("Grupo:");

        grupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grupoKeyTyped(evt);
            }
        });

        registrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        regresar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        regresar.setText("Cancelar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        horario.setLayout(new java.awt.GridLayout(7, 3, 5, 5));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Día");
        horario.add(jLabel6);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Hora Inicio");
        horario.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Hora Fin");
        horario.add(jLabel10);

        lunes.setText("Lunes");
        lunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunesActionPerformed(evt);
            }
        });
        horario.add(lunes);

        inicioLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        horario.add(inicioLunes);

        finLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        horario.add(finLunes);

        martes.setText("Martes");
        martes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                martesActionPerformed(evt);
            }
        });
        horario.add(martes);

        inicioMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        horario.add(inicioMartes);

        finMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        horario.add(finMartes);

        miercoles.setText("Miercoles");
        miercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miercolesActionPerformed(evt);
            }
        });
        horario.add(miercoles);

        inicioMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        horario.add(inicioMiercoles);

        finMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        horario.add(finMiercoles);

        jueves.setText("Jueves");
        jueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juevesActionPerformed(evt);
            }
        });
        horario.add(jueves);

        inicioJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        horario.add(inicioJueves);

        finJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        horario.add(finJueves);

        viernes.setText("Viernes");
        viernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viernesActionPerformed(evt);
            }
        });
        horario.add(viernes);

        inicioViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        horario.add(inicioViernes);

        finViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        horario.add(finViernes);

        sabado.setText("Sabado");
        sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoActionPerformed(evt);
            }
        });
        horario.add(sabado);

        inicioSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00" }));
        horario.add(inicioSabado);

        finSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00" }));
        horario.add(finSabado);

        javax.swing.GroupLayout AltaLayout = new javax.swing.GroupLayout(Alta);
        Alta.setLayout(AltaLayout);
        AltaLayout.setHorizontalGroup(
            AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AltaLayout.createSequentialGroup()
                        .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(docentes, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AltaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(AltaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))))
                    .addGroup(AltaLayout.createSequentialGroup()
                        .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(horario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AltaLayout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(regresar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AltaLayout.setVerticalGroup(
            AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(5, 5, 5)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horario, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrar)
                    .addComponent(regresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Alta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Alta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        //Pregunta si quiere ingresar el dato
        int reply = JOptionPane.showConfirmDialog(this, "¿Seguro que desea registrar esta información?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            Curso cursos = null;
            // Agregamos el curso a la base de datos
            CursoDAO cursoDAO = new CursoDAO();
            cursoDAO.abrirSSH();
            cursoDAO.abrirConexion();

            boolean validar = true;
            //Busca por si estan checkado los dias y los manda a validar
            for (int i = 0; i < checkDias.size() && validar; i++) {
                if (checkDias.get(i).isSelected()) {
                    String dia = checkDias.get(i).getText().toUpperCase();
                    String hrInicio = inicios.get(i).getSelectedItem().toString() + ":00";
                    String hrFin = fines.get(i).getSelectedItem().toString() + ":00";
                    validar = validarTraslape(dia, hrInicio, hrFin);
                }
            }
            System.out.println(validar);
            //Si no es valido o el vacante ingresa el horario
            if (validar || docente.getNumEmpleado().equals("000000")) {
                try {
                    //Se necesita corregir
                    cursos = new Curso(docente, materia, grupo.getText(), tipo.getSelectedItem().toString().toUpperCase(), 0, 0);
                    //Agrega el curso
                    cursoDAO.append(cursos);
                } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
                    JOptionPane.showMessageDialog(this, "Ya existe un curso registrado \n" + ex.toString(),
                            "INFORMANDO", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) { //Error en general
                    JOptionPane.showMessageDialog(this, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Agregamos los horarios junto con el curso horario a la base de datos por dias
                    asignarHorario(cursos);
                    cursoDAO.cerrarSSH();
                }
            } else {
                System.out.println("XD");
            }
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        cerrrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrrarVentana();
    }//GEN-LAST:event_formWindowClosing

    /* Estas acciones son para verificar si el checkbox del dia esta habilitado 
       y asi poder selecinar la hora de inicio y fin*/
    private void lunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lunesActionPerformed
        if (lunes.isSelected()) {
            inicioLunes.setEnabled(true);
            finLunes.setEnabled(true);
        } else {
            inicioLunes.setEnabled(false);
            finLunes.setEnabled(false);
        }
    }//GEN-LAST:event_lunesActionPerformed

    private void martesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_martesActionPerformed
        if (martes.isSelected()) {
            inicioMartes.setEnabled(true);
            finMartes.setEnabled(true);
        } else {
            inicioMartes.setEnabled(false);
            finMartes.setEnabled(false);
        }
    }//GEN-LAST:event_martesActionPerformed

    private void miercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miercolesActionPerformed
        if (miercoles.isSelected()) {
            inicioMiercoles.setEnabled(true);
            finMiercoles.setEnabled(true);
        } else {
            inicioMiercoles.setEnabled(false);
            finMiercoles.setEnabled(false);
        }
    }//GEN-LAST:event_miercolesActionPerformed

    private void juevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juevesActionPerformed
        if (jueves.isSelected()) {
            inicioJueves.setEnabled(true);
            finJueves.setEnabled(true);
        } else {
            inicioJueves.setEnabled(false);
            finJueves.setEnabled(false);
        }
    }//GEN-LAST:event_juevesActionPerformed

    private void viernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viernesActionPerformed
        if (viernes.isSelected()) {
            inicioViernes.setEnabled(true);
            finViernes.setEnabled(true);
        } else {
            inicioViernes.setEnabled(false);
            finViernes.setEnabled(false);
        }
    }//GEN-LAST:event_viernesActionPerformed

    private void sabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoActionPerformed
        if (sabado.isSelected()) {
            inicioSabado.setEnabled(true);
            finSabado.setEnabled(true);
        } else {
            inicioSabado.setEnabled(false);
            finSabado.setEnabled(false);
        }
    }//GEN-LAST:event_sabadoActionPerformed
    /**
     * TERMINAN ACCIONES DE LA SEMANA
     *
     */


    private void docentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docentesActionPerformed
        String nombreDocente;
        for (Profesor profesor : profeDAO) {
            nombreDocente = profesor.getNumEmpleado() + " " + profesor.getNom();
            if (docentes.getSelectedItem().equals(nombreDocente)) {
                docente = profesor;
            }
        }
    }//GEN-LAST:event_docentesActionPerformed

    private void materiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiasActionPerformed
        String nombreMateria;
        for (Materia materia1 : materiaDAO) {
            nombreMateria = materia1.getClaveMateria() + " " + materia1.getNom();
            if (materias.getSelectedItem().equals(nombreMateria)) {
                materia = materia1;
            }
        }
    }//GEN-LAST:event_materiasActionPerformed

    private void grupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grupoKeyTyped

    }//GEN-LAST:event_grupoKeyTyped

    /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrrarVentana() {
        principal.setVisible(true);
        dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Alta;
    private javax.swing.JPanel Titulo;
    private javax.swing.JComboBox<String> docentes;
    private javax.swing.JComboBox<String> finJueves;
    private javax.swing.JComboBox<String> finLunes;
    private javax.swing.JComboBox<String> finMartes;
    private javax.swing.JComboBox<String> finMiercoles;
    private javax.swing.JComboBox<String> finSabado;
    private javax.swing.JComboBox<String> finViernes;
    private javax.swing.JTextField grupo;
    private javax.swing.JPanel horario;
    private javax.swing.JComboBox<String> inicioJueves;
    private javax.swing.JComboBox<String> inicioLunes;
    private javax.swing.JComboBox<String> inicioMartes;
    private javax.swing.JComboBox<String> inicioMiercoles;
    private javax.swing.JComboBox<String> inicioSabado;
    private javax.swing.JComboBox<String> inicioViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jueves;
    private javax.swing.JCheckBox lunes;
    private javax.swing.JCheckBox martes;
    private javax.swing.JComboBox<String> materias;
    private javax.swing.JCheckBox miercoles;
    private javax.swing.JButton registrar;
    private javax.swing.JButton regresar;
    private javax.swing.JCheckBox sabado;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JCheckBox viernes;
    // End of variables declaration//GEN-END:variables
}
