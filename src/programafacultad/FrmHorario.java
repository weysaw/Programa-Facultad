package programafacultad;

import conexion.*;
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
    private boolean seleccionaDia;
    private boolean seleccionaHora;
    //Se crea el objeto para la conexión
    private final CursoHorarioDAO dao;

    /**
     * Constructor de la clase horario
     */
    public FrmHorario(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        seleccionaDia = false;
        seleccionaHora = false;
        dao = new CursoHorarioDAO();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Horarios");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Horarios Facultad de Ingenieria");

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        semestre.setText("Semestre Actual: ");

        datosHorarios.setAutoCreateRowSorter(true);
        datosHorarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        datosHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Docente", "Num. Grupo", "Materia"
            }
        ));
        datosHorarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        datosHorarios.setEnabled(false);
        datosHorarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(datosHorarios);

        jLabel3.setText("Dia:");

        jLabel4.setText("Hora:");

        jLabel5.setText("Tipo:");

        dia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" }));
        dia.setSelectedIndex(-1);
        dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaActionPerformed(evt);
            }
        });

        horas.setSelectedIndex(-1);
        horas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horasActionPerformed(evt);
            }
        });

        tipoClase.setSelectedIndex(-1);
        tipoClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
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
                        .addComponent(tipoClase, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(semestre)
                                .addGap(96, 96, 96)
                                .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(regresar))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
                                .addGap(37, 37, 37)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(semestre)))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regresar)
                .addContainerGap())
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
        if (!seleccionaDia) {
            String docente, materia, nomDia, grupo, hora;
            //Se utiliza para agregarlo al combobox tipo Clase
            HashSet<String> tipo = new HashSet();
            //Se devuelve el día de la semana por el combobox
            nomDia = dia.getSelectedItem().toString().toUpperCase();
            //Se devuelve la hora por del combobox que esta seleccionado
            hora = horas.getSelectedItem().toString();
            //Se le indica que esta usando las horas
            seleccionaHora = true;
            //Abre las conexiones
            dao.abrirSSH();
            dao.abrirConexion();
            //Remueve todas las filas de la tabla
            removerFilas();
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
                JOptionPane.showMessageDialog(this, "No se pudo leer la base de datos\n" + e.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                //Cierra la conexión
                dao.cerrarSSH();
            }
            //Le indica que ya no esta presionando la lista de horas
            seleccionaHora = false;
        }
    }//GEN-LAST:event_horasActionPerformed

    private void diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaActionPerformed
        String docente, materia, nomDia, grupo;
        nomDia = dia.getSelectedItem().toString().toUpperCase();
        //Se utiliza para agregarlo al combobox de horas 
        HashSet<Integer> datos = new HashSet();
        //Abre las conexiones
        dao.abrirSSH();
        dao.abrirConexion();
        //Le indica que esta usando la lista de días
        seleccionaDia = true;
        //Remueve las filas de la tabla
        removerFilas();
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
            JOptionPane.showMessageDialog(this, "No se pudo leer la base de datos\n" + e.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            //Cierra la conexión
            dao.cerrarSSH();
        }
        //Le indica que ya no esta presionando la lista de días
        seleccionaDia = false;
    }//GEN-LAST:event_diaActionPerformed

    private void tipoClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoClaseActionPerformed
        //Verifica si esta seleccionandose la hora o los día
        if (!seleccionaHora && !seleccionaDia) {
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
                JOptionPane.showMessageDialog(this, "No se pudo leer la base de datos\n" + e.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                dao.cerrarSSH();
            }
        }
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
    private javax.swing.JTable datosHorarios;
    private javax.swing.JComboBox<String> dia;
    private javax.swing.JComboBox<String> horas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresar;
    private javax.swing.JLabel semestre;
    private javax.swing.JLabel semestreActual;
    private javax.swing.JComboBox<String> tipoClase;
    // End of variables declaration//GEN-END:variables
}
