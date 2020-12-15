package programafacultad;

import conexion.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.*;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 * Es la ventana que muestra los horarios
 *
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo
 * @version 05.12.2020
 */
public class FrmHorario extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<CursoHorario> cursos;
    //Se crea el objeto para la conexión
    private final CursoHorarioDAO dao;

    /**
     * Constructor de la clase horario
     */
    public FrmHorario(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        dao = new CursoHorarioDAO();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        consultas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        regresar = new javax.swing.JButton();
        semestre = new javax.swing.JLabel();
        semestreActual = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        datosHorarios = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dia = new javax.swing.JComboBox<>();
        horas = new javax.swing.JComboBox<>();
        tipoClase = new javax.swing.JComboBox<>();
        Titulo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Horarios");
        setMinimumSize(new java.awt.Dimension(650, 550));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Horarios Facultad de Ingeniería");

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        semestre.setText("Semestre Actual: ");

        semestreActual.setText("20XX-X");

        jScrollPane1.setToolTipText("");

        datosHorarios.setAutoCreateRowSorter(true);
        datosHorarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        datosHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Docente", "Num. Grupo", "Materia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datosHorarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        datosHorarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        datosHorarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(datosHorarios);

        jLabel3.setText("Día:");

        jLabel4.setText("Hora de Inicio:");

        jLabel5.setText("Tipo:");

        dia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" }));
        dia.setSelectedIndex(-1);
        dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaActionPerformed(evt);
            }
        });

        horas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horasActionPerformed(evt);
            }
        });

        tipoClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout consultasLayout = new javax.swing.GroupLayout(consultas);
        consultas.setLayout(consultasLayout);
        consultasLayout.setHorizontalGroup(
            consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(consultasLayout.createSequentialGroup()
                        .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consultasLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(semestre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(consultasLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tipoClase, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(regresar)))
                .addContainerGap())
        );
        consultasLayout.setVerticalGroup(
            consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(semestre))
                    .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regresar)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Facultad de Ingeniería Campus Mexicali");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficos/uabc.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Planta Académica FIM");

        javax.swing.GroupLayout TituloLayout = new javax.swing.GroupLayout(Titulo);
        Titulo.setLayout(TituloLayout);
        TituloLayout.setHorizontalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TituloLayout.setVerticalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TituloLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrarVentana();
    }//GEN-LAST:event_formWindowClosing

    private void horasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horasActionPerformed
        //Crea el mensaje 
        MensajeEspera mensaje = new MensajeEspera(this) {
            //Sobrescribe el método y le indica que debe hacer
            @Override
            public void accion(Component cmp) {
                String docente, materia, nomDia, grupo, hora;
                //Se utiliza para agregarlo al combobox tipo Clase
                HashSet<String> tipo = new HashSet();
                //Se devuelve el día de la semana por el combobox
                nomDia = dia.getSelectedItem().toString().toUpperCase();
                //Se devuelve la hora por del combobox que esta seleccionado
                hora = horas.getSelectedItem().toString();
                //Abre las conexiones
                dao.abrirSSH();
                dao.abrirConexion();
                //Remueve todas las filas de la tabla
                removerFilas();
                //Quita los listeners para que no hagan nada
                tipoClase.removeActionListener(tipoClase.getActionListeners()[0]);
                //Remueve los objetos que tenga el combobox del tipo clase
                tipoClase.removeAllItems();
                try {
                    //Devuelve el arreglo con el día y la hora en especifico
                    cursos = dao.readDiaHoraClase("'" + nomDia + "'", "'" + hora + ":00'", "tipo");
                    //Recorre todo el arreglo y agrega los datos a la tabla
                    for (CursoHorario curso : cursos) {
                        //Devuelve el atributo para cada cosa en la tabla
                        docente = curso.getCurso().getProfesor().getNom();
                        grupo = curso.getCurso().getGrupo();
                        materia = curso.getCurso().getMateria().getNom();
                        //Agrega el dato a la tabla
                        agregarFila(docente, grupo, materia);
                        //Se indica para que el combobox tenga los datos correctos sin repeticion
                        tipo.add(curso.getCurso().getTipo());
                    }
                    //Usa un arraylist para ordenar los datos de orden ascendente
                    ArrayList<String> ordenados = new ArrayList(tipo);
                    //Ordena los datos
                    Collections.sort(ordenados);
                    //Recorre todos los datos ordenados y los agrega a la lista
                    for (String ordenado : ordenados) {
                        tipoClase.addItem(ordenado);
                    }

                    tipoClase.setSelectedIndex(-1);
                } catch (Exception e) {
                    //Mensaje de error
                    JOptionPane.showMessageDialog(cmp, "No se pudo leer la base de datos\n" + e.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    //Cierra la conexión
                    dao.cerrarSSH();
                }
                tipoClase.addActionListener((e) -> {
                    tipoClaseActionPerformed(e);
                });
            }
        };
        //Realiza la accion y muestra el mensaje
        mensaje.mostrarMensaje();
    }//GEN-LAST:event_horasActionPerformed

    private void diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaActionPerformed
        //Crea el mensaje 
        MensajeEspera mensaje = new MensajeEspera(this) {
            //Sobrescribe el método y le indica que debe hacer
            @Override
            public void accion(Component cmp) {
                String docente, materia, nomDia, grupo;
                nomDia = dia.getSelectedItem().toString().toUpperCase();
                //Se utiliza para agregarlo al combobox de horas 
                HashSet<Integer> datos = new HashSet();
                //Abre las conexiones
                dao.abrirSSH();
                dao.abrirConexion();
                //Remueve las filas de la tabla
                removerFilas();
                //Quita los listeners para que no hagan nada
                horas.removeActionListener(horas.getActionListeners()[0]);
                tipoClase.removeActionListener(tipoClase.getActionListeners()[0]);
                //Remueve los objetos de los combobox
                horas.removeAllItems();
                tipoClase.removeAllItems();

                try {
                    //Lee los cursos que tengan el mismo nombre del día
                    cursos = dao.readDiaHoraClase("'" + nomDia + "'", "hrInicio", "tipo");

                    //Recorre todo el arreglo y agrega los datos a la tabla
                    for (CursoHorario curso : cursos) {
                        docente = curso.getCurso().getProfesor().getNom();
                        grupo = curso.getCurso().getGrupo();
                        materia = curso.getCurso().getMateria().getNom();
                        agregarFila(docente, grupo, materia);
                        //Agrega los datos de la hora a lista que no permite repeticiones
                        datos.add(curso.getHorario().getHrInicio().getHours());
                    }
                    //Usa un arraylist para ordenar los datos de orden ascendente
                    ArrayList<Integer> ordenados = new ArrayList(datos);
                    //Ordena los datos
                    Collections.sort(ordenados);
                    //Recorre todo el arreglo y lo agrega al combobox
                    for (int ordenado : ordenados) {
                        horas.addItem(ordenado + ":00");
                    }
                    horas.setSelectedIndex(-1);
                    tipoClase.setSelectedIndex(-1);
                } catch (Exception e) {
                    //Mensaje de error
                    JOptionPane.showMessageDialog(cmp, "No se pudo leer la base de datos\n" + e.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    //Cierra la conexión
                    dao.cerrarSSH();
                }
                horas.addActionListener((e) -> horasActionPerformed(e));
                tipoClase.addActionListener((e) -> tipoClaseActionPerformed(e));
            }
        };
        //Realiza la accion y muestra el mensaje
        mensaje.mostrarMensaje();


    }//GEN-LAST:event_diaActionPerformed

    private void tipoClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoClaseActionPerformed
        //Verifica si esta seleccionandose la hora o los día
        MensajeEspera mensaje = new MensajeEspera(this) {
            @Override
            public void accion(Component cmp) {
                String docente, materia, nomDia, grupo, hora, tipo;
                //Devuelve el día que esta seleccionado en la lista
                nomDia = dia.getSelectedItem().toString().toUpperCase();
                //Devuelve la hora que esta seleccionado en la lista
                hora = horas.getSelectedItem().toString();
                //Devuelve el tipo de clase que esta seleccionado en la lista            
                tipo = tipoClase.getSelectedItem().toString();
                //Abre las conexiones
                dao.abrirSSH();
                dao.abrirConexion();
                try {
                    //Remuevo todas las filas 
                    removerFilas();
                    cursos = dao.readDiaHoraClase("'" + nomDia + "'", "'" + hora + ":00'", "'" + tipo + "'");
                    for (CursoHorario curso : cursos) {
                        docente = curso.getCurso().getProfesor().getNom();//Esto se debe de hacer con quearys
                        grupo = curso.getCurso().getGrupo();
                        materia = curso.getCurso().getMateria().getNom();
                        agregarFila(docente, grupo, materia);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(cmp, "No se pudo leer la base de datos\n" + e.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    dao.cerrarSSH();
                }
            }
        };
        mensaje.mostrarMensaje();
    }//GEN-LAST:event_tipoClaseActionPerformed
    /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrarVentana() {
        principal.setVisible(true);
        dispose();
    }

    /**
     * Agrega la fila a la tabla
     *
     * @param docente Es el nombre de la materia
     * @param grupo Es el grupo de la materia
     * @param materia Es el nombre de la materia que se agrega
     */
    private void agregarFila(String docente, String grupo, String materia) {
        DefaultTableModel modelo = (DefaultTableModel) datosHorarios.getModel();
        modelo.addRow(new Object[]{docente, grupo, materia});
    }

    /**
     * Remueve todas las filas de la tabla
     */
    private void removerFilas() {
        DefaultTableModel modelo = (DefaultTableModel) datosHorarios.getModel();
        modelo.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Titulo;
    private javax.swing.JPanel consultas;
    private javax.swing.JTable datosHorarios;
    private javax.swing.JComboBox<String> dia;
    private javax.swing.JComboBox<String> horas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresar;
    private javax.swing.JLabel semestre;
    private javax.swing.JLabel semestreActual;
    private javax.swing.JComboBox<String> tipoClase;
    // End of variables declaration//GEN-END:variables
}
