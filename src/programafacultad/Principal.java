package programafacultad;

import conexion.*;
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
        initComponents();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.out.println(ex.toString());
        }
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

    private void consultaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaHorarioActionPerformed
        FrmHorario horario = new FrmHorario(this);
        horario.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaHorarioActionPerformed

    private void consultaMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaMaestroActionPerformed
        FrmMaestro maestro = new FrmMaestro(this);
        maestro.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaMaestroActionPerformed

    private void consultaMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaMateriaActionPerformed
        FrmMateria materia = new FrmMateria(this);
        materia.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaMateriaActionPerformed

    private void consultaHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaHorasActionPerformed
        FrmHoras hora = new FrmHoras(this, true);
        hora.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_consultaHorasActionPerformed

    private void altaMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaMaestroActionPerformed
        AltaMaestro alta = new AltaMaestro(this);
        alta.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_altaMaestroActionPerformed

    private void altaMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaMateriaActionPerformed
        AltaMateria alta = new AltaMateria(this);
        alta.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_altaMateriaActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        int proporcion = 40;
        Font fuente = new Font("Arial", 1, 1 * getWidth() / proporcion);
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
        LectorTxt lector = new LectorTxt();
        if (lector.recuperarDatos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ERROR AL RECUPERAR ARCHIVOS", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            ProfesorDAO profesorDAO = new ProfesorDAO();
            try {
                profesorDAO.deleteAll();
                System.out.println("DATOS ELIMINADOS PROFESOR");
                for (Profesor profesor : lector.getProfesor()) {
                    profesorDAO.append(profesor);
                    System.out.println("Agregado ---> " + profesor);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR BASE DE DATOS\n" + e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                profesorDAO.cerrarSSH();
            }

            MateriaDAO materiaDAO = new MateriaDAO();
            try {
                materiaDAO.deleteAll();
                System.out.println("DATOS ELIMINADOS MATERIA");
                for (Materia materia : lector.getMateria()) {
                    materiaDAO.append(materia);
                    System.out.println("Agregado ---> " + materia);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR LEER BASE DE DATOS\n" + e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                materiaDAO.cerrarSSH();
            }

            HorarioDAO horarioDAO = new HorarioDAO();
            try {
                horarioDAO.deleteAll();
                System.out.println("DATOS ELIMINADOS HORARIOS");
                for (Horario horario : lector.getHorario()) {
                    horarioDAO.append(horario);
                    System.out.println("Agregado ---> " + horario);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "ERROR LEER BASE DE DATOS\n" + e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                horarioDAO.cerrarSSH();
            }

            CursoDAO cursoDAO = new CursoDAO();
            try {
                cursoDAO.deleteAll();
                System.out.println("DATOS ELIMINADOS CURSO");
                for (Curso curso : lector.getCurso()) {
                    System.out.println("Agregado ---> " + curso);
                    cursoDAO.append(curso);
                }
            } catch (Exception e) {
                System.out.println("Repetido");
                JOptionPane.showMessageDialog(this, "ERROR CURSO BD\n" + e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                cursoDAO.cerrarSSH();
            }
            CursoHorarioDAO cursoHorarioDAO = new CursoHorarioDAO();
            try {
                cursoHorarioDAO.deleteAll();
                System.out.println("DATOS ELIMINADOS CURSO_HORARIO");
                for (CursoHorario cursoHorario : lector.getCursoHorario()) {
                    cursoHorarioDAO.append(cursoHorario);
                    System.out.println("Agregado ---> " + cursoHorario);
                }
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(this, "ERROR CURSO_HORARIO\n" + e.getMessage(), "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                cursoHorarioDAO.cerrarSSH();
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
