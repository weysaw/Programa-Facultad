package programafacultad;

import conexion.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Time;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Component;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Da de alta los cursos y los usuarios
 *
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo, Perez Valdez Raymundo
 * @version 15.12.2020
 */
public class ModificarCursoHorario extends javax.swing.JFrame {

    private final Principal principal;
    private final ArrayList<JCheckBox> checkDias;
    private final ArrayList<JComboBox> fines;
    private final ArrayList<JComboBox> inicios;
    private ArrayList<CursoHorario> cursosHorariosActuales;

    /**
     * Constructor de la clase
     */
    public ModificarCursoHorario(Principal principal) {
        try {
            setIconImage(ImageIO.read(getClass().getResource("/graficos/uabc.png"))); //Icono del programa
        } catch (IOException ex) {
            Logger.getLogger(ModificarMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        //Inicializa las relaciones de los checkbox y combobox
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
        //Se abren las conexiones
        materiaConexion.abrirSSH();
        materiaConexion.abrirConexion();
        materias.removeActionListener(materias.getActionListeners()[0]);
        materias.removeAllItems();
        try {
            ArrayList<Materia> materiaDAO = materiaConexion.readAll();
            for (Materia materia1 : materiaDAO) {
                materias.addItem(materia1.getClaveMateria() + " " + materia1.getNom());
            }
            if (materiaDAO.isEmpty()) {
                cerrarVentana();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR LECTURA MATERIA\n" + ex.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            cerrarVentana();
        } finally { // Cierra la conexión SSH
            materiaConexion.cerrarSSH();
            materias.addActionListener((e) -> materiasActionPerformed(e));
            materias.setSelectedIndex(-1);
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
        CursoHorarioDAO cursoHDAO = new CursoHorarioDAO();
        cursoHDAO.abrirConexion();
        try {
            for (CursoHorario cursosHorariosActuale : cursosHorariosActuales) 
                cursoHDAO.delete(cursosHorariosActuale);
            
            // Agregamos los horarios junto con el curso horario a la base de datos por dias
            for (int i = 0; i < 6; i++) {
                if (checkDias.get(i).isSelected()) {
                    agregarCursoHorario(cursos, inicios.get(i), fines.get(i), checkDias.get(i));
                }
            }
        } catch (Exception e) {//Error en general
            JOptionPane.showMessageDialog(this, "ERROR\n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Valida si el horario no se ha traslapado
     *
     * @param dia Es el dia que se verifica
     * @param numEmpleado Es el numEmpleado a que se verifica
     * @return Devuelve true si no esta en la lista y false no no esta
     */
    private boolean validarTraslape(String dia, String hrInicio, String hrFin, String hrIniVieja, String hrFinVieja) {
        CursoHorarioDAO dao = new CursoHorarioDAO();
        dao.abrirConexion();
        ArrayList<CursoHorario> curso = null;
        try {
            //Busca el horario en la base de datos con el Queary indicado
            String numEmpleado = docentes.getSelectedItem().toString().split(" ")[0];
            curso = dao.readHrIntervaloModificar(dia, hrInicio, hrFin, hrIniVieja, hrFinVieja, numEmpleado);
            if (curso.isEmpty()) {
                return true;
            }
            String diaTexto;
            for (CursoHorario cursoHorario : curso) {
                System.out.println(cursoHorario.getHorario());
                diaTexto = cursoHorario.getHorario().getDia();
                JOptionPane.showMessageDialog(this, "Hay un traslape en:\nDía: " + diaTexto,
                        "TRASLAPE", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
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
        botonModificar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        grupo = new javax.swing.JComboBox<>();
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
        eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar cursos");
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
        jLabel1.setText("Modificar/Baja Cursos");

        jLabel3.setText("Docente:");

        jLabel4.setText("Materia:");

        jLabel5.setText("Tipo:");

        docentes.setEnabled(false);
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

        tipo.setEnabled(false);
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        jLabel2.setText("Grupo:");

        botonModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.setEnabled(false);
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        regresar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        regresar.setText("Cancelar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        grupo.setEnabled(false);
        grupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoActionPerformed(evt);
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
        lunes.setEnabled(false);
        lunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunesActionPerformed(evt);
            }
        });
        horario.add(lunes);

        inicioLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        inicioLunes.setEnabled(false);
        horario.add(inicioLunes);

        finLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        finLunes.setEnabled(false);
        horario.add(finLunes);

        martes.setText("Martes");
        martes.setEnabled(false);
        martes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                martesActionPerformed(evt);
            }
        });
        horario.add(martes);

        inicioMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        inicioMartes.setEnabled(false);
        horario.add(inicioMartes);

        finMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        finMartes.setEnabled(false);
        horario.add(finMartes);

        miercoles.setText("Miercoles");
        miercoles.setEnabled(false);
        miercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miercolesActionPerformed(evt);
            }
        });
        horario.add(miercoles);

        inicioMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        inicioMiercoles.setEnabled(false);
        horario.add(inicioMiercoles);

        finMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        finMiercoles.setEnabled(false);
        horario.add(finMiercoles);

        jueves.setText("Jueves");
        jueves.setEnabled(false);
        jueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juevesActionPerformed(evt);
            }
        });
        horario.add(jueves);

        inicioJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        inicioJueves.setEnabled(false);
        horario.add(inicioJueves);

        finJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        finJueves.setEnabled(false);
        horario.add(finJueves);

        viernes.setText("Viernes");
        viernes.setEnabled(false);
        viernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viernesActionPerformed(evt);
            }
        });
        horario.add(viernes);

        inicioViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));
        inicioViernes.setEnabled(false);
        horario.add(inicioViernes);

        finViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));
        finViernes.setEnabled(false);
        horario.add(finViernes);

        sabado.setText("Sabado");
        sabado.setEnabled(false);
        sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoActionPerformed(evt);
            }
        });
        horario.add(sabado);

        inicioSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00" }));
        inicioSabado.setEnabled(false);
        horario.add(inicioSabado);

        finSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00" }));
        finSabado.setEnabled(false);
        horario.add(finSabado);

        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AltaLayout = new javax.swing.GroupLayout(Alta);
        Alta.setLayout(AltaLayout);
        AltaLayout.setHorizontalGroup(
            AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AltaLayout.createSequentialGroup()
                        .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AltaLayout.createSequentialGroup()
                            .addComponent(eliminar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(regresar))
                        .addComponent(horario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AltaLayout.createSequentialGroup()
                            .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(docentes, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(AltaLayout.createSequentialGroup()
                                    .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(68, 68, 68))
                                .addComponent(grupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(140, 140, 140))
        );
        AltaLayout.setVerticalGroup(
            AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(5, 5, 5)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horario, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonModificar)
                    .addComponent(regresar)
                    .addComponent(eliminar))
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
                        .addComponent(Alta, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    /**
     * Verifica que las horas de inicio y fin de un día no sean iguales,
     * ni que la hora de inicio sea después que la de fin
     * @param dia El día cuyas horas verificar
     * @param horaInicio La hora de inicio
     * @param horaFin La hora de fin
     * @return false si no hay problemas con las horas, true si hay algún problema
    */
    private boolean verificarHoras(JCheckBox dia, JComboBox horaInicio, JComboBox horaFin) {
        //Verifica que las horas de inicio y fin de un día no sean iguales, ni que la hora de inicio sea después que la de fin
        if (dia.isSelected()) {
            String inicioS = horaInicio.getSelectedItem().toString();
            inicioS = inicioS.substring(0, inicioS.indexOf(':'));
            int inicioI = Integer.parseInt(inicioS);
            String finS = horaFin.getSelectedItem().toString();
            finS = finS.substring(0, finS.indexOf(':'));
            int finI = Integer.parseInt(finS);
            if (inicioI == finI || inicioI > finI)
                return true;
        }
        return false;
    }
    
    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if (!lunes.isSelected() && !martes.isSelected() && !miercoles.isSelected() && !jueves.isSelected() && !viernes.isSelected() && !sabado.isSelected()) {
            JOptionPane.showMessageDialog(this, "Seleccione al menos un día.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        //Verificar que las horas de inicio sean antes que las horas de fin
        boolean error = false;
        if (verificarHoras(lunes, inicioLunes, finLunes)) {
            JOptionPane.showMessageDialog(this, "LUNES: la hora de inicio debe ser antes que la de fin.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            error = true;
        }
        if (verificarHoras(martes, inicioMartes, finMartes)) {
            JOptionPane.showMessageDialog(this, "MARTES: la hora de inicio debe ser antes que la de fin.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            error = true;
        }
        if (verificarHoras(miercoles, inicioMiercoles, finMiercoles)) {
            JOptionPane.showMessageDialog(this, "MIÉRCOLES: la hora de inicio debe ser antes que la de fin.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            error = true;
        }
        if (verificarHoras(jueves, inicioJueves, finJueves)) {
            JOptionPane.showMessageDialog(this, "JUEVES: la hora de inicio debe ser antes que la de fin.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            error = true;
        }
        if (verificarHoras(viernes, inicioViernes, finViernes)) {
            JOptionPane.showMessageDialog(this, "VIERNES: la hora de inicio debe ser antes que la de fin.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            error = true;
        }
        if (verificarHoras(sabado, inicioSabado, finSabado)) {
            JOptionPane.showMessageDialog(this, "SÁBADO: la hora de inicio debe ser antes que la de fin.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            error = true;
        }
        
        if (error) return;
        
        //Pregunta si quiere ingresar el dato
        int reply = JOptionPane.showConfirmDialog(this, "¿Seguro que desea modificar esta información?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MensajeEspera mensaje = new MensajeEspera(this) {
                @Override
                public void accion(Component cmp) {
                Curso cursos = null;
                // Agregamos el curso a la base de datos
                CursoDAO cursoDAO = new CursoDAO();
                //Abre las conexiones
                cursoDAO.abrirSSH();
                cursoDAO.abrirConexion();
                boolean validar = true;
                String hrInicioVieja = null, hrFinVieja = null;
                //Busca por si estan checkado los dias y los manda a validar
                for (int i = 0; i < checkDias.size() && validar; i++) {
                    if (checkDias.get(i).isSelected()) {
                        for (CursoHorario cursosHorariosActuale : cursosHorariosActuales) {
                            if (cursosHorariosActuale.getHorario().getDia().equals(checkDias.get(i).getText().toUpperCase())) {
                                hrInicioVieja = cursosHorariosActuale.getHorario().getHrInicio().getHours() + ":00";
                                hrFinVieja = cursosHorariosActuale.getHorario().getHrFin().getHours() + ":00";
                            }
                        }
                        String dia = checkDias.get(i).getText().toUpperCase();
                        String hrInicio = inicios.get(i).getSelectedItem().toString();
                        String hrFin = fines.get(i).getSelectedItem().toString();
                        validar = validarTraslape(dia, hrInicio, hrFin, hrInicioVieja, hrFinVieja);
                    }
                }
                System.out.println(validar);

                //Si no es valido o el vacante ingresa el horario
                String numEmpleado = getProfeSel();
                int claveMateria = getClaveMateria();
                if (validar || numEmpleado.equals("000000")) {
                    try {
                        MateriaDAO leerMateriaDAO = new MateriaDAO();
                        ProfesorDAO leerProfesorDAO = new ProfesorDAO();
                        leerMateriaDAO.setConexion(cursoDAO.getConexionBD());
                        leerProfesorDAO.setConexion(cursoDAO.getConexionBD());
                        Materia materia = leerMateriaDAO.read(new Materia(claveMateria, null));
                        Profesor docente = leerProfesorDAO.read(new Profesor(numEmpleado, null, false));

                        cursos = new Curso(docente, materia, grupo.getSelectedItem().toString(),
                                tipo.getSelectedItem().toString().toUpperCase(), 0, 0);

                        // Agregamos los horarios junto con el curso horario a la base de datos por dias
                        asignarHorario(cursos);
                        JOptionPane.showMessageDialog(cmp, "Modificado con éxito.", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
                        JOptionPane.showMessageDialog(cmp, "Ya existe un curso registrado \n" + ex.toString(),
                                "INFORMANDO", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) { //Error en general
                        JOptionPane.showMessageDialog(cmp, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        cursoDAO.cerrarSSH();
                    }
                } else {
                    JOptionPane.showMessageDialog(cmp, "Hay un traslape en el horario", "Traslape", JOptionPane.INFORMATION_MESSAGE);
                    cursoDAO.cerrarSSH();
                }
                }
            };
            mensaje.mostrarMensaje();
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrarVentana();
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

    private void sabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sabadoActionPerformed
        if (sabado.isSelected()) {
            inicioSabado.setEnabled(true);
            finSabado.setEnabled(true);
        } else {
            inicioSabado.setEnabled(false);
            finSabado.setEnabled(false);
        }
    }//GEN-LAST:event_sabadoActionPerformed

    private void juevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juevesActionPerformed
        if (jueves.isSelected()) {
            inicioJueves.setEnabled(true);
            finJueves.setEnabled(true);
        } else {
            inicioJueves.setEnabled(false);
            finJueves.setEnabled(false);
        }
    }//GEN-LAST:event_juevesActionPerformed

    private void miercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miercolesActionPerformed
        if (miercoles.isSelected()) {
            inicioMiercoles.setEnabled(true);
            finMiercoles.setEnabled(true);
        } else {
            inicioMiercoles.setEnabled(false);
            finMiercoles.setEnabled(false);
        }
    }//GEN-LAST:event_miercolesActionPerformed

    private void viernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viernesActionPerformed
        if (viernes.isSelected()) {
            inicioViernes.setEnabled(true);
            finViernes.setEnabled(true);
        } else {
            inicioViernes.setEnabled(false);
            finViernes.setEnabled(false);
        }
    }//GEN-LAST:event_viernesActionPerformed

    private void materiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiasActionPerformed
        if (materias.getSelectedIndex() == -1) {
            eliminar.setEnabled(false);
            botonModificar.setEnabled(false);
            docentes.setEnabled(false);
            docentes.setSelectedIndex(-1);
            return;
        }
        MensajeEspera mensaje = new MensajeEspera(this) {
            @Override
            public void accion(Component cmp) {
                //Crea el objeto de la conexión
                CursoHorarioDAO CHdao = new CursoHorarioDAO();
                //Abre las conexiones
                CHdao.abrirSSH();
                CHdao.abrirConexion();
                try {
                    //Remueve las acciones de las lista momentaneamente
                    docentes.removeActionListener(docentes.getActionListeners()[0]);
                    tipo.removeActionListener(tipo.getActionListeners()[0]);
                    grupo.removeActionListener(grupo.getActionListeners()[0]);
                    //Remueve las listas 
                    docentes.removeAllItems();
                    tipo.removeAllItems();
                    grupo.removeAllItems();
                    //Desactiva los combobox
                    tipo.setEnabled(false);
                    grupo.setEnabled(false);
                    //Desactiva el boton
                    botonModificar.setEnabled(false);
                    eliminar.setEnabled(false);
                    //Desactiva los indicadores de las horas
                    desactivarHoras();
                    //Leer la clave de la materia
                    String claveMateria = getClaveMateria() + "";
                    //Lee cursos horarios con la materia especificada
                    ArrayList<CursoHorario> cursosHorarios = CHdao.readMateria(claveMateria);
                    //Si esta vacia el arreglo se sale
                    if (cursosHorarios.isEmpty()) {
                        JOptionPane.showMessageDialog(cmp, "NO HAY HORARIOS DE CURSOS REGISTRADOS", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        cerrarVentana();
                    }
                    //Lista para que no se repita
                    HashSet<String> profesores = new HashSet();
                    for (CursoHorario cursosHorario : cursosHorarios) {
                        //Obtiene los datos
                        String numEmpleado = cursosHorario.getCurso().getProfesor().getNumEmpleado();
                        String nombreEmpleado = cursosHorario.getCurso().getProfesor().getNom();
                        //Agrega el profesor en el hash
                        profesores.add(numEmpleado + " " + nombreEmpleado);
                    }
                    //Recorre todo el hashset y agrega los datos al combobox
                    for (String profesore : profesores) {
                        docentes.addItem(profesore);
                    }
                } catch (Exception e) {
                    //Mensaje de error
                    JOptionPane.showMessageDialog(cmp, "Error en leer profesores\n" + e.getMessage(), "ERROR",
                            JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    //Cierro la conexión
                    CHdao.cerrarSSH();
                    docentes.setSelectedIndex(-1);
                    //Habilita el combobox de docentes
                    docentes.setEnabled(true);
                    //Agrega las acciones a los combobox
                    docentes.addActionListener((evt) -> docentesActionPerformed(evt));
                    tipo.addActionListener((e) -> tipoActionPerformed(e));
                    grupo.addActionListener((e) -> grupoActionPerformed(e));
                }
            }
        };
        //Ejecuta el mensaje de espera y ejecuta las acciones
        mensaje.mostrarMensaje();
    }//GEN-LAST:event_materiasActionPerformed

    private void docentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docentesActionPerformed
        if (docentes.getSelectedIndex() == -1) {
            tipo.setEnabled(false);
            tipo.setSelectedIndex(-1);
            return;
        }
        MensajeEspera mensaje = new MensajeEspera(this) {
            @Override
            public void accion(Component cmp) {
                //Crea el objeto de la conexión
                CursoHorarioDAO CHdao = new CursoHorarioDAO();
                //Abre las conexiones
                CHdao.abrirSSH();
                CHdao.abrirConexion();
                //Remueve las acciones de las lista momentaneamente
                tipo.removeActionListener(tipo.getActionListeners()[0]);
                grupo.removeActionListener(grupo.getActionListeners()[0]);
                //Remueve las listas 
                grupo.removeAllItems();
                tipo.removeAllItems();
                //Desactiva el boton de registrar
                botonModificar.setEnabled(false);
                eliminar.setEnabled(false);
                desactivarHoras();
                try {
                    //Obtiene los datos
                    String claveMateria = getClaveMateria() + "";
                    String numEmpleado = getProfeSel();

                    //Obtienes los horarios del profesor en especifico
                    ArrayList<CursoHorario> cursosHorarios = CHdao.readEspecifico(claveMateria, numEmpleado, "tipo", "grupo");
                    HashSet<String> tipoDeClase = new HashSet();
                    //Recorre los cursos horarios y agrega los tipos de clase
                    for (CursoHorario cursosHorario : cursosHorarios) {
                        tipoDeClase.add(cursosHorario.getCurso().getTipo());
                    }
                    //Agrega los tipos de clase a la lista
                    for (String string : tipoDeClase) {
                        tipo.addItem(string);
                    }

                } catch (Exception e) {
                    //Muestra el mensaje de error
                    JOptionPane.showMessageDialog(cmp, "Error en leer profesores\n" + e.getMessage(),
                            "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    //Cierra la conexión SSH
                    CHdao.cerrarSSH();
                    tipo.setSelectedIndex(-1);
                    //Activa el combobox de tipo
                    tipo.setEnabled(true);
                    //Asigna las acciones de nuevo
                    tipo.addActionListener((evt) -> tipoActionPerformed(evt));
                    grupo.addActionListener((e) -> grupoActionPerformed(e));
                }
            }
        };
        mensaje.mostrarMensaje();
    }//GEN-LAST:event_docentesActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        if (tipo.getSelectedIndex() == -1) {
            grupo.setEnabled(false);
            grupo.setSelectedIndex(-1);
            return;
        }
        MensajeEspera mensaje = new MensajeEspera(this) {
            @Override
            public void accion(Component cmp) {
                CursoHorarioDAO CHdao = new CursoHorarioDAO();
                //Abre las conexiones
                CHdao.abrirSSH();
                CHdao.abrirConexion();
                //Remueve las acciones del grupo
                grupo.removeActionListener(grupo.getActionListeners()[0]);
                //Quita todos los itemtas
                grupo.removeAllItems();
                //Desactiva el boton 
                botonModificar.setEnabled(false);
                eliminar.setEnabled(false);
                desactivarHoras();
                try {
                    //Obtiene los datos necesarios
                    String claveMateria = getClaveMateria() + "";
                    String numEmpleado = getProfeSel();
                    String tipos = tipo.getSelectedItem().toString();

                    //Lee todos los cursos horarios indicados 
                    ArrayList<CursoHorario> cursosHorarios = CHdao.readEspecifico(claveMateria,
                            numEmpleado, "'" + tipos + "'", "grupo");
                    //Lista para que no se repitan los datos
                    HashSet<String> grupos = new HashSet();
                    //Recorre todos los cursos y los agrega al hashset siempre y cuando no se repitan
                    for (CursoHorario cursosHorario : cursosHorarios) {
                        grupos.add(cursosHorario.getCurso().getGrupo());
                    }
                    //Agrega los grupos que da el profesor
                    for (String string : grupos) {
                        grupo.addItem(string);
                    }
                } catch (Exception e) {
                    //Muestra el mensaje de error
                    JOptionPane.showMessageDialog(cmp, "Error en leer profesores\n" + e.getMessage(),
                            "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    //Cierra la conexión
                    CHdao.cerrarSSH();
                    grupo.setSelectedIndex(-1);
                    //Activa la lista de grupos
                    grupo.setEnabled(true);
                    //Agrega las acciones
                    grupo.addActionListener((e) -> grupoActionPerformed(e));
                }
            }
        };
        mensaje.mostrarMensaje();
    }//GEN-LAST:event_tipoActionPerformed

    private void grupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoActionPerformed
        if (grupo.getSelectedIndex() == -1) {
            lunes.setEnabled(false);
            martes.setEnabled(false);
            miercoles.setEnabled(false);
            jueves.setEnabled(false);
            viernes.setEnabled(false);
            sabado.setEnabled(false);
            bloqueo();
            return;
        }
        MensajeEspera mensaje = new MensajeEspera(this) {
            @Override
            public void accion(Component cmp) {
                CursoHorarioDAO CHdao = new CursoHorarioDAO();
                //Abre la conexión 
                CHdao.abrirSSH();
                CHdao.abrirConexion();
                try {
                    botonModificar.setEnabled(true);
                    eliminar.setEnabled(true);
                    //Obtiene los datos
                    String claveMateria = getClaveMateria() + "";
                    String numEmpleado = getProfeSel();
                    String tipos = tipo.getSelectedItem().toString();
                    String grupoTexto = grupo.getSelectedItem().toString();
                    //Lee los cursos actuales
                    cursosHorariosActuales = CHdao.readEspecifico(claveMateria, numEmpleado,
                            "'" + tipos + "'", "'" + grupoTexto + "'");
                    activarHoras();
                    //Recorre todos los cursos del horario
                    for (CursoHorario cursosHorario : cursosHorariosActuales) {
                        for (int i = 0; i < inicios.size(); i++) {
                            //Compara el texto de los check y pregunta si el mismo que el del curso horario
                            if (checkDias.get(i).getText().toUpperCase().equals(cursosHorario.getHorario().getDia())) {
                                //Activa los objetos indicados
                                checkDias.get(i).setSelected(true);
                                inicios.get(i).setEnabled(true);
                                fines.get(i).setEnabled(true);
                                //Devuelve el horario que tiene en el semestre
                                inicios.get(i).setSelectedItem(cursosHorario.getHorario().getHrInicio().getHours() + ":00");
                                fines.get(i).setSelectedItem(cursosHorario.getHorario().getHrFin().getHours() + ":00");
                                break;
                            }
                        }
                    }
                    //Revisa todos los check y y los pone en falso
                    for (JCheckBox checkDia : checkDias) {
                        if (!checkDia.isEnabled() && checkDia.isSelected()) {
                            checkDia.setSelected(false);
                        }
                    }

                } catch (Exception e) {
                    //Mensaje de error
                    JOptionPane.showMessageDialog(cmp, "Error en leer profesores\n" + e.getMessage(),
                            "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } finally {
                    //Cierra la sesión
                    CHdao.cerrarSSH();
                }
            }
        };
        mensaje.mostrarMensaje();
    }//GEN-LAST:event_grupoActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int reply = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar este curso?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MensajeEspera mensaje = new MensajeEspera(this) {
                @Override
                public void accion(Component cmp) {
                CursoDAO cursoDAO = new CursoDAO();
                cursoDAO.abrirSSH();
                cursoDAO.abrirConexion();
                try {
                    //Obtiene los parámetros del curso a eliminar.
                    String numEmpleado = (String)docentes.getSelectedItem();
                    numEmpleado = numEmpleado.substring(0, numEmpleado.indexOf(' '));
                    String claveMateriaS = (String)materias.getSelectedItem();
                    claveMateriaS = claveMateriaS.substring(0, claveMateriaS.indexOf(' '));
                    int claveMateria = Integer.parseInt(claveMateriaS);
                    String grp = (String)grupo.getSelectedItem();
                    String tpo = (String)tipo.getSelectedItem();
                    cursoDAO.delete(numEmpleado, claveMateria, grp, tpo);
                    JOptionPane.showMessageDialog(cmp, "Eliminado exitosamente.", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) { //Error en general
                    JOptionPane.showMessageDialog(cmp, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally { //Cierra el ssh
                    cursoDAO.cerrarSSH();
                    //Re-obtiene la información de los cursos registrados
                    informacion();
                }
                }
            };
            mensaje.mostrarMensaje();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrarVentana() {
        principal.setVisible(true);
        dispose();
    }

    /**
     * Devuelve el profe seleccionado
     *
     */
    private String getProfeSel() {
        return docentes.getSelectedItem().toString().split(" ")[0];
    }

    /**
     * Devuelve la clave de la materia seleccionada
     *
     */
    private int getClaveMateria() {
        return Integer.parseInt(materias.getSelectedItem().toString().split(" ")[0]);
    }

    /**
     * Desactiva las horas de los check y los combobox
     */
    private void desactivarHoras() {
        for (int i = 0; i < inicios.size(); i++) {
            inicios.get(i).setEnabled(false);
            fines.get(i).setEnabled(false);
            checkDias.get(i).setEnabled(false);
            checkDias.get(i).setSelected(false);
        }
    }

    /**
     * Desactiva las horas de los check y los combobox
     */
    private void activarHoras() {
        for (int i = 0; i < inicios.size(); i++) {
            checkDias.get(i).setEnabled(true);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Alta;
    private javax.swing.JPanel Titulo;
    private javax.swing.JButton botonModificar;
    private javax.swing.JComboBox<String> docentes;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<String> finJueves;
    private javax.swing.JComboBox<String> finLunes;
    private javax.swing.JComboBox<String> finMartes;
    private javax.swing.JComboBox<String> finMiercoles;
    private javax.swing.JComboBox<String> finSabado;
    private javax.swing.JComboBox<String> finViernes;
    private javax.swing.JComboBox<String> grupo;
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
    private javax.swing.JButton regresar;
    private javax.swing.JCheckBox sabado;
    private javax.swing.JComboBox<String> tipo;
    private javax.swing.JCheckBox viernes;
    // End of variables declaration//GEN-END:variables
}
