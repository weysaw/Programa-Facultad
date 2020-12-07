package programafacultad;

import conexion.*;
import java.sql.Connection;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import registrodeclases.LectorTxt;

/**
 * La clase principal del programa
 *
 * @author Leslie Vidal
 * @version 03.12.2020
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Constructor del principal
     */
    public Principal() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.out.println(ex.toString());
        }
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        consultaHorario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        consultaMateria = new javax.swing.JButton();
        consultaMaestro = new javax.swing.JButton();
        consultaHoras = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        altaMaestro = new javax.swing.JButton();
        altaMateria = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        leerArchivo = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Programa Consultas");
        setMinimumSize(new java.awt.Dimension(526, 500));
        setPreferredSize(new java.awt.Dimension(469, 500));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        consultaHorario.setText("Consulta por Horario");
        consultaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaHorarioActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Facultad De Ingenieria Campus Mexicali");

        consultaMateria.setText("Consulta por Materia");
        consultaMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaMateriaActionPerformed(evt);
            }
        });

        consultaMaestro.setText("Consulta por Maestro");
        consultaMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaMaestroActionPerformed(evt);
            }
        });

        consultaHoras.setText("Numeros");
        consultaHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaHorasActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Dar de alta");

        altaMaestro.setText("Alta Docente");
        altaMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaMaestroActionPerformed(evt);
            }
        });

        altaMateria.setFont(altaMateria.getFont().deriveFont(altaMateria.getFont().getSize()+1f));
        altaMateria.setText("Alta Materia");
        altaMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaMateriaActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Leer Archivos");

        leerArchivo.setText("Leer Cursos, Horarios y Docentes");
        leerArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(396, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(leerArchivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(consultaHorario, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                    .addComponent(consultaHoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(consultaMaestro, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(consultaMateria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(altaMateria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(71, 71, 71)
                                .addComponent(altaMaestro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(58, 58, 58))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(consultaHorario)
                        .addGap(18, 18, 18)
                        .addComponent(consultaHoras))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(consultaMateria)
                        .addGap(18, 18, 18)
                        .addComponent(consultaMaestro)))
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(altaMateria)
                    .addComponent(altaMaestro))
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(leerArchivo)
                .addGap(94, 94, 94))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {altaMaestro, altaMateria, consultaHoras, consultaMaestro, consultaMateria, leerArchivo});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Inicia la ventana de la consulta horario y la hace visible
     *
     */
    private void consultaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaHorarioActionPerformed
        //Hace visible la consulta horario
        FrmHorario horario = new FrmHorario(this);
        horario.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaHorarioActionPerformed
    /**
     * Inicia la ventana de la consulta maestro y la hace visible
     *
     */
    private void consultaMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaMaestroActionPerformed
        //Hace visible la consulta horario
        FrmMaestro maestro = new FrmMaestro(this);
        maestro.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaMaestroActionPerformed
    /**
     * Inicia la ventana de la consulta materia y la hace visible
     *
     */
    private void consultaMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaMateriaActionPerformed
        //Hace visible la consulta horario
        FrmMateria materia = new FrmMateria(this);
        materia.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaMateriaActionPerformed
    /**
     * Inicia la ventana de la consulta horas y la hace visible
     *
     */
    private void consultaHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaHorasActionPerformed
        //Hace visible la consulta horario
        FrmHoras hora = new FrmHoras(this, true);
        hora.setVisible(true);
    }//GEN-LAST:event_consultaHorasActionPerformed
    /**
     * Inicia la ventana de la alta maestro y la hace visible
     */
    private void altaMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaMaestroActionPerformed
        //Hace visible la consulta horario
        AltaMaestro alta = new AltaMaestro(this);
        alta.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_altaMaestroActionPerformed
    /**
     * Inicia la ventana de la alta materia y la hace visible
     *
     */
    private void altaMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaMateriaActionPerformed
        AltaMateria alta = new AltaMateria(this);
        alta.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_altaMateriaActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        //Sirve para tener una proporción de las letras
        int proporcion = 40;
        //Crea la fuente
        Font fuente = new Font("Arial", 1, 1 * getWidth() / proporcion);
        //Asigna el tamaño de letra a cada boton y label
        consultaHorario.setFont(fuente);
        consultaHoras.setFont(fuente);
        consultaMaestro.setFont(fuente);
        consultaMateria.setFont(fuente);
        altaMaestro.setFont(fuente);
        altaMateria.setFont(fuente);
        leerArchivo.setFont(fuente);
        jLabel1.setFont(fuente);
        jLabel2.setFont(fuente);
        jLabel3.setFont(fuente);
    }//GEN-LAST:event_formComponentResized

    private void leerArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerArchivoActionPerformed
        //Crear el lector de texto
        LectorTxt lector = new LectorTxt();
        //Recupera los datos del archivo y si esta vacio le marca error
        if (lector.recuperarDatos().isEmpty()) {
            //Muestra los mensajes de error
            JOptionPane.showMessageDialog(this, "ERROR AL RECUPERAR ARCHIVOS", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            //Si el archivo no esta vacio lee los datos
        } else {
            //Crear el objeto de profesor
            ProfesorDAO profesorDAO = new ProfesorDAO();
            //Se crea el objeto que comunica con la tabla
            MateriaDAO materiaDAO = new MateriaDAO();
            //Se crea el objeto que comunica con la tabla
            HorarioDAO horarioDAO = new HorarioDAO();
            //Se crea el objeto que comunica con la tabla
            CursoHorarioDAO cursoHorarioDAO = new CursoHorarioDAO();
            //Abre la conexión ssh
            profesorDAO.abrirSSH();
            //Abre la conexión a la base de datos
            profesorDAO.abrirConexion();
            try {
                Connection conexion = profesorDAO.getConexionBD();
                //Borra todos los elementos de la tabla
                profesorDAO.deleteAll();
                System.out.println("DATOS ELIMINADOS PROFESOR");
                //Recorre todo el arreglo del archivo que manda
                for (Profesor profesor : lector.getProfesor()) {
                    //Ingresa la información a la base de datos 
                    profesorDAO.append(profesor);
                    //System.out.println("Agregado ---> " + profesor);
                }
                //Abre la conexión a la base de datos
                materiaDAO.setConexion(conexion);
                try {
                    //Borra todos los elementos de la tabla
                    materiaDAO.deleteAll();
                    System.out.println("DATOS ELIMINADOS MATERIA");
                    //Recorre todo el arreglo del archivo que manda
                    for (Materia materia : lector.getMateria()) {
                        //Ingresa la información a la base de datos 
                        materiaDAO.append(materia);
                        //System.out.println("Agregado ---> " + materia);
                    }
                    //Abre la conexión a la base de datos
                    //horarioDAO.abrirConexion();
                    horarioDAO.setConexion(conexion);
                    try {
                        //Borra todos los elementos de la tabla
                        horarioDAO.deleteAll();
                        System.out.println("DATOS ELIMINADOS HORARIOS");
                        //Recorre todo el arreglo del archivo que manda
                        for (Horario horario : lector.getHorario()) {
                            //Ingresa la información a la base de datos 
                            horarioDAO.append(horario);
                            //System.out.println("Agregado ---> " + horario);
                        }
                        //Se crea el objeto que comunica con la tabla
                        CursoDAO cursoDAO = new CursoDAO();
                        //Abre la conexión a la base de datos
                        //cursoDAO.abrirConexion();
                        cursoDAO.setConexion(conexion);
                        try {
                            //Borra todos los elementos de la tabla
                            cursoDAO.deleteAll();
                            System.out.println("DATOS ELIMINADOS CURSO");
                            //Recorre todo el arreglo del archivo que manda
                            for (Curso curso : lector.getCurso()) {
                                //Ingresa la información a la base de datos 
                                cursoDAO.append(curso);
                                //System.out.println("Agregado ---> " + curso);
                            }
                            //Abre la conexión a la base de datos
                            //horarioDAO.abrirConexion();
                            horarioDAO.setConexion(conexion);
                            //Abre la conexión a la base de datos
                            //cursoHorarioDAO.abrirConexion();
                            cursoHorarioDAO.setConexion(conexion);
                            try {
                                //Borra todos los elementos de la tabla
                                cursoHorarioDAO.deleteAll();
                                System.out.println("DATOS ELIMINADOS CURSO_HORARIO");
                                //Recorre todo el arreglo del archivo que manda
                                for (CursoHorario cursoHorario : lector.getCursoHorario()) {
                                    cursoHorario.setHorario(horarioDAO.read(cursoHorario.getHorario()));
                                    //Ingresa la información a la base de datos 
                                    cursoHorarioDAO.append(cursoHorario);
                                    //System.out.println("Agregado ---> " + cursoHorario);
                                }
                            } catch (Exception e) {
                                //Muestra los mensajes de error
                                System.out.println(e.toString());
                                JOptionPane.showMessageDialog(this, "ERROR CURSO_HORARIO\n" + e.getMessage(), "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            JOptionPane.showMessageDialog(this, "SE HAN AGREGADO LOS DATOS", "DATOS AGREGADOS",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception e) {
                            //Muestra los mensajes de error
                            System.out.println(e.toString());
                            JOptionPane.showMessageDialog(this, "ERROR CURSO BD\n" + e.getMessage(), "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        //Muestra los mensajes de error
                        System.out.println(e.toString());
                        JOptionPane.showMessageDialog(this, "ERROR LEER BASE DE DATOS\n" + e.getMessage(), "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    //Muestra los mensajes de error
                    System.out.println(e.toString());
                    JOptionPane.showMessageDialog(this, "ERROR LEER BASE DE DATOS\n" + e.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                //Muestra los mensajes de error
                System.out.println(e.toString());
                JOptionPane.showMessageDialog(this, "ERROR BASE DE DATOS\n" + e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                //Cierra la conexion con el SSH
                profesorDAO.cerrarSSH();
            }
        }
    }//GEN-LAST:event_leerArchivoActionPerformed

    /**
     * Ejecución del programa
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Principal principal = new Principal();
        principal.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton altaMaestro;
    private javax.swing.JButton altaMateria;
    private javax.swing.JButton consultaHorario;
    private javax.swing.JButton consultaHoras;
    private javax.swing.JButton consultaMaestro;
    private javax.swing.JButton consultaMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton leerArchivo;
    // End of variables declaration//GEN-END:variables
}
