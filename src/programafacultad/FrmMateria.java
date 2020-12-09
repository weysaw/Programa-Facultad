package programafacultad;
import conexion.*;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.sql.Time;

/**
 * Muestra la información de la materia
 * 
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo
 * @version 03.12.2020
 */
public class FrmMateria extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<CursoHorario> cursos;
    //Se crea el objeto para la conexión
    private final CursoHorarioDAO dao;
    //Constantes
    private final byte DOCENTE = 0;
    private final byte GRUPO = 1;
    private final byte TIPO = 2;
    private final byte LUNES = 3;
    private final byte MARTES = 4;
    private final byte MIERCOLES = 5;
    private final byte JUEVES = 6;
    private final byte VIERNES = 7;
    private final byte SABADO = 8;
    
    /**
     * Constructor de la clase
     */
    public FrmMateria(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        dao = new CursoHorarioDAO();
        construyeLista();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        regresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        semestreActual = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        materias = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        datosMaterias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Materia");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Materias Facultad de Ingenieria");

        jLabel3.setText("Semestre Actual: ");

        jLabel1.setText("Materia: ");

        materias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiasActionPerformed(evt);
            }
        });

        datosMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Docente", "Grupo", "Tipo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"
            }
        ));
        datosMaterias.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(datosMaterias);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(materias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(155, 155, 155)))
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(337, Short.MAX_VALUE)
                .addComponent(regresar)
                .addContainerGap(338, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
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

    private void materiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiasActionPerformed
        String materia, docente, grupo, tipo, dia, hrInicio, hrFin,
                numEmpleadoPrevio = "";
        int fila;
        
        materia = materias.getSelectedItem().toString().toUpperCase();
        //Abre las conexiones
        dao.abrirSSH();
        dao.abrirConexion();
        //Remueve las filas de la tabla
        removerFilas();
        try {
            //Lee los cursos que tengan la materia especificada
            cursos = dao.readMateria("'" + materia + "'");
            
            //Recorre todo el arreglo y agrega los datos a la tabla
            for (CursoHorario curso : cursos) {
                docente = curso.getCurso().getProfesor().getNom();
                grupo = curso.getCurso().getGrupo();
                tipo = curso.getCurso().getTipo();
                dia = curso.getHorario().getDia();
                hrInicio = curso.getHorario().getHrInicio().toString();
                hrFin = curso.getHorario().getHrFin().toString();
                
                //Agrega cada registro a la fila
                DefaultTableModel modelo = (DefaultTableModel) datosMaterias.getModel();
                fila = modelo.getRowCount();
                if (fila == 0) {
                    //Siempre agrega el primer registro como la primera fila
                    numEmpleadoPrevio = curso.getCurso().getProfesor().getNumEmpleado();
                    agregarFila(docente, grupo, tipo, dia, hrInicio, hrFin,
                            modelo);
                } else {
                    fila--;
                    /* Si el registro actual es del mismo curso que el registro anterior
                       pero con un dia y hora diferente, agrega la hora a la columna del
                       día indicado del renglón del registro anterior.
                       Como puede haber varios profesores con el mismo nombre completo,
                       es necesario compararlos por sus números de empleado.
                    */
                    if (curso.getCurso().getProfesor().getNumEmpleado().equals(numEmpleadoPrevio) &&
                            modelo.getValueAt(fila, GRUPO).equals(grupo) &&
                            modelo.getValueAt(fila, TIPO).equals(tipo)) {
                        actualizarFila(dia, hrInicio, hrFin, modelo);
                    } else {
                        numEmpleadoPrevio = curso.getCurso().getProfesor().getNumEmpleado();
                        agregarFila(docente, grupo, tipo, dia, hrInicio, hrFin,
                            modelo);
                    }
                }
            }
        } catch (Exception e) {
            //Mensaje de error
            JOptionPane.showMessageDialog(this, "No se pudo leer la base de datos\n" + e.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            //Cierra la conexión
            dao.cerrarSSH();
        }
    }//GEN-LAST:event_materiasActionPerformed
    
    //Agrega las materias registradas a la lista de materias
    private void construyeLista() {
        /* Desactiva el listener temporalmente para evitar que corra el evento
           al agregar nuevos objetos al comboBox
        */
        materias.removeActionListener(materias.getActionListeners()[0]);
        //Abre las conexiones
        MateriaDAO dao = new MateriaDAO();
        dao.abrirSSH();
        dao.abrirConexion();
        //Consigue la lista
        try {
            ArrayList<Materia> listaMaterias = dao.readAll();
            //Recorre el arreglo y agrega cada materia a la lista
            for (Materia materia : listaMaterias) {
                materias.addItem(materia.getNom());
            }
        } catch (Exception e) {
            //Mensaje de error
            JOptionPane.showMessageDialog(this, "No se pudo conseguir la lista de "
                    + "materias registradas\n" + e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            //Cierra la conexión
            dao.cerrarSSH();
        }
        //Reactiva el listener
        materias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiasActionPerformed(evt);
            }
        });
    }
    
    /**
     * Agrega la fila a la tabla
     *
     * @param docente El nombre del docente que imparte el curso
     * @param grupo El grupo del curso impartido
     * @param tipo El tipo del curso impartido
     * @param dia El dia en el que se imparte el curso
     * @param hrInicio La hora de inicio del curso
     * @param hrFin La hora de finalización del curso
     * @param modelo La tabla en la que se agregará la fila
     */
    private void agregarFila(String docente, String grupo, String tipo, String dia,
                                String hrInicio, String hrFin, DefaultTableModel modelo) {
        modelo.addRow(new Object[]{docente, grupo, tipo});
        actualizarFila(dia, hrInicio, hrFin, modelo);
    }
    
    /**
     * Actualiza la fila más reciente de la tabla
     *
     * @param dia El dia en el que se imparte el curso
     * @param hrInicio La hora de inicio del curso
     * @param hrFin La hora de finalización del curso
     * @param modelo La tabla cuya fila se actualizará
     */
    private void actualizarFila(String dia, String hrInicio, String hrFin,
            DefaultTableModel modelo) {
        //Quita los segundos de las horas, ej: 08:00:00 -> 08:00
        hrInicio = hrInicio.substring(0, hrInicio.length()-3);
        hrFin = hrFin.substring(0, hrFin.length()-3);
        
        switch (dia) {
            case "LUNES":
                modelo.setValueAt(hrInicio+"-"+hrFin, modelo.getRowCount()-1, LUNES);
                break;
            case "MARTES":
                modelo.setValueAt(hrInicio+"-"+hrFin, modelo.getRowCount()-1, MARTES);
                break;
            case "MIERCOLES":
                modelo.setValueAt(hrInicio+"-"+hrFin, modelo.getRowCount()-1, MIERCOLES);
                break;
            case "JUEVES":
                modelo.setValueAt(hrInicio+"-"+hrFin, modelo.getRowCount()-1, JUEVES);
                break;
            case "VIERNES":
                modelo.setValueAt(hrInicio+"-"+hrFin, modelo.getRowCount()-1, VIERNES);
                break;
            case "SABADO":
                modelo.setValueAt(hrInicio+"-"+hrFin, modelo.getRowCount()-1, SABADO);
                break;
        }
    }
    
     /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrarVentana() {
        principal.setVisible(true);
        dispose();
    }
    
    /**
     * Remueve todas las filas de la tabla
     */
    private void removerFilas() {
        DefaultTableModel modelo = (DefaultTableModel) datosMaterias.getModel();
        modelo.setRowCount(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable datosMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> materias;
    private javax.swing.JButton regresar;
    private javax.swing.JLabel semestreActual;
    // End of variables declaration//GEN-END:variables
}
