package programafacultad;

import conexion.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Time;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 * Da de alta los cursos y los usuarios
 *
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo
 * @version 08.12.2020
 */
public class AltaCursoHorario extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<Materia> materiaDAO;
    private ArrayList<Profesor> profeDAO;
    private Profesor docente;
    private Materia materia;

    /**
     * Constructor de la clase
     */
    public AltaCursoHorario(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        materiaDAO = new ArrayList();
        profeDAO = new ArrayList();
        bloqueo();
        informacion();
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
                dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERROR LECTURA MATERIA\n" + ex.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            dispose();
        } finally { // Cierra la conexión SSH
            materiaConexion.cerrarSSH();
        }
    }

    private void agregarCursoHorario(Curso cursos, JComboBox<String> hrInicioDia, JComboBox<String> hrFinDia, JCheckBox dia) {
        //Convierte las horas del dia
        String hrFin = hrFinDia.getSelectedItem().toString().split(":")[0];
        String hrInicio = hrInicioDia.getSelectedItem().toString().split(":")[0];
        int horaFin = Integer.parseInt(hrFin);
        int horaInicio = Integer.parseInt(hrInicio);

        Time tiempoInicio = new Time(horaInicio, 0, 0);
        Time tiempoFin = new Time(horaFin, 0, 0);
        String diaTexto = dia.getText().toUpperCase();

        Horario horario = new Horario(diaTexto, tiempoInicio, tiempoFin);

        HorarioDAO horarioDAO = new HorarioDAO();
        horarioDAO.abrirConexion();
        try {
            Horario repetido = horarioDAO.read(horario);
            if (repetido == null) {
                horarioDAO.append(horario);
                System.out.println("SE REGISTRO EL HORARIO");
                horario = horarioDAO.read(horario);
            } else {
                horario = repetido;
            }
            CursoHorario cursoH = new CursoHorario(cursos, horario);
            CursoHorarioDAO cursoHDAO = new CursoHorarioDAO();
            cursoHDAO.setConexion(horarioDAO.getConexionBD());
            try {
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

    private void asignarHorario(Curso cursos) {
        // Agregamos los horarios junto con el curso horario a la base de datos por dias
        if (lunes.isSelected()) {
            agregarCursoHorario(cursos, inicioLunes, finLunes, lunes);
        }
        if (martes.isSelected()) {
            agregarCursoHorario(cursos, inicioMartes, finMartes, martes);
        }
        if (miercoles.isSelected()) {
            agregarCursoHorario(cursos, inicioMiercoles, finMiercoles, miercoles);
        }
        if (jueves.isSelected()) {
            agregarCursoHorario(cursos, inicioJueves, finJueves, jueves);
        }
        if (viernes.isSelected()) {
            agregarCursoHorario(cursos, inicioViernes, finViernes, viernes);
        }
        if (sabado.isSelected()) {
            agregarCursoHorario(cursos, inicioSabado, finSabado, sabado);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dias = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        docentes = new javax.swing.JComboBox<>();
        materias = new javax.swing.JComboBox<>();
        tipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        grupo = new javax.swing.JTextField();
        lunes = new javax.swing.JCheckBox();
        martes = new javax.swing.JCheckBox();
        miercoles = new javax.swing.JCheckBox();
        jueves = new javax.swing.JCheckBox();
        viernes = new javax.swing.JCheckBox();
        sabado = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        horasTC = new javax.swing.JTextField();
        horasAsig = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        inicioLunes = new javax.swing.JComboBox<>();
        inicioMartes = new javax.swing.JComboBox<>();
        inicioMiercoles = new javax.swing.JComboBox<>();
        inicioJueves = new javax.swing.JComboBox<>();
        inicioViernes = new javax.swing.JComboBox<>();
        inicioSabado = new javax.swing.JComboBox<>();
        finLunes = new javax.swing.JComboBox<>();
        finMartes = new javax.swing.JComboBox<>();
        finMiercoles = new javax.swing.JComboBox<>();
        finJueves = new javax.swing.JComboBox<>();
        finViernes = new javax.swing.JComboBox<>();
        finSabado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Alta de Cursos");

        jLabel3.setText("Docente:");

        jLabel4.setText("Materia:");

        jLabel5.setText("Tipo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Horario");

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

        lunes.setText("Lunes");
        lunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lunesActionPerformed(evt);
            }
        });

        martes.setText("Martes");
        martes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                martesActionPerformed(evt);
            }
        });

        miercoles.setText("Miercoles");
        miercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miercolesActionPerformed(evt);
            }
        });

        jueves.setText("Jueves");
        jueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juevesActionPerformed(evt);
            }
        });

        viernes.setText("Viernes");
        viernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viernesActionPerformed(evt);
            }
        });

        sabado.setText("Sabado");
        sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sabadoActionPerformed(evt);
            }
        });

        jLabel7.setText("Horas TC");

        jLabel8.setText("Horas Asig");

        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Hora Inicio");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Hora Fin");

        inicioLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));

        inicioMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));

        inicioMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));

        inicioJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));

        inicioViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00" }));

        inicioSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00" }));

        finLunes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        finMartes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        finMiercoles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        finJueves.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        finViernes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        finSabado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(martes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(miercoles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jueves, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viernes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sabado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lunes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9)
                                    .addComponent(inicioLunes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inicioMartes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inicioMiercoles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inicioJueves, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inicioViernes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inicioSabado, 0, 75, Short.MAX_VALUE))
                                .addGap(69, 69, 69))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(horasTC, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(registrar)
                                            .addComponent(horasAsig, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(97, 97, 97)
                                        .addComponent(regresar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(189, 189, 189)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(docentes, 0, 365, Short.MAX_VALUE)
                                .addComponent(materias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(finLunes, javax.swing.GroupLayout.Alignment.LEADING, 0, 78, Short.MAX_VALUE)
                        .addComponent(finMartes, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finMiercoles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finSabado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finViernes, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finJueves, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(docentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lunes)
                            .addComponent(inicioLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(martes)
                            .addComponent(inicioMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(miercoles)
                            .addComponent(inicioMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jueves)
                            .addComponent(inicioJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viernes)
                            .addComponent(inicioViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inicioSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sabado)
                            .addComponent(finSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horasTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(horasAsig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regresar)
                            .addComponent(registrar))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar esta información?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            Curso cursos = null;
            // Agregamos el curso a la base de datos
            CursoDAO cursoDAO = new CursoDAO();
            cursoDAO.abrirSSH();
            cursoDAO.abrirConexion();
            try {
                cursos = new Curso(docente, materia, grupo.getText(), tipo.getSelectedItem().toString().toUpperCase(),
                        Integer.parseInt(horasTC.getText()), Integer.parseInt(horasAsig.getText()));
                cursoDAO.append(cursos);
            } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
                JOptionPane.showMessageDialog(this, "Ya existe un curso registrado \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) { //Error en general
                JOptionPane.showMessageDialog(this, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Agregamos los horarios junto con el curso horario a la base de datos por dias
                asignarHorario(cursos);
                cursoDAO.cerrarSSH();
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

    /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrrarVentana() {
        principal.setVisible(true);
        dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup dias;
    private javax.swing.JComboBox<String> docentes;
    private javax.swing.JComboBox<String> finJueves;
    private javax.swing.JComboBox<String> finLunes;
    private javax.swing.JComboBox<String> finMartes;
    private javax.swing.JComboBox<String> finMiercoles;
    private javax.swing.JComboBox<String> finSabado;
    private javax.swing.JComboBox<String> finViernes;
    private javax.swing.JTextField grupo;
    private javax.swing.JTextField horasAsig;
    private javax.swing.JTextField horasTC;
    private javax.swing.JComboBox<String> inicioJueves;
    private javax.swing.JComboBox<String> inicioLunes;
    private javax.swing.JComboBox<String> inicioMartes;
    private javax.swing.JComboBox<String> inicioMiercoles;
    private javax.swing.JComboBox<String> inicioSabado;
    private javax.swing.JComboBox<String> inicioViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
