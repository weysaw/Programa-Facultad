
package programafacultad;
import conexion.CursoHorario;
import conexion.CursoHorarioDAO;
import conexion.Materia;
import conexion.MateriaDAO;
import conexion.Profesor;
import conexion.ProfesorDAO;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Muestra los horarios y materias de un maestro
 * 
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo 
 * @version 03.12.2020
 */
public class FrmMaestro extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<CursoHorario> cursos;
    //Se crea el objeto para la conexión
    private final CursoHorarioDAO dao;
    //Constantes
    private final byte MATERIA = 0;
    private final byte GRUPO = 1;
    private final byte TIPO = 2;
    private final byte LUNES = 3;
    private final byte MARTES = 4;
    private final byte MIERCOLES = 5;
    private final byte JUEVES = 6;
    private final byte VIERNES = 7;
    private final byte SABADO = 8;
    private final byte TOTAL_HORAS = 9;
    
    /**
     * Constructor de la clase
     */
    public FrmMaestro(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        dao = new CursoHorarioDAO();
        construyeLista();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        consulta = new javax.swing.JPanel();
        regresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        semestreActual = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        datosDocente = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        docentes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        horasTotales = new javax.swing.JLabel();
        Titulo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Maestro");
        setMinimumSize(new java.awt.Dimension(656, 564));
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
        jLabel2.setText("Docentes Facultad de Ingenieria");

        jLabel3.setText("Semestre Actual: ");

        semestreActual.setText("20XX-X");

        jScrollPane1.setEnabled(false);

        datosDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Materia", "Grupo", "Tipo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Total Horas"
            }
        ));
        datosDocente.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(datosDocente);

        jLabel4.setText("Docente:");

        docentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docentesActionPerformed(evt);
            }
        });

        jLabel1.setText("Horas totales impartidas:");

        horasTotales.setText("0");

        javax.swing.GroupLayout consultaLayout = new javax.swing.GroupLayout(consulta);
        consulta.setLayout(consultaLayout);
        consultaLayout.setHorizontalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(docentes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(consultaLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(horasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(consultaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(regresar)))
                        .addGap(22, 22, 22))))
        );
        consultaLayout.setVerticalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(docentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(horasTotales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(regresar)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Facultad De Ingenieria Campus Mexicali ");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficos/uabc.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("<Titulo del programa>");

        javax.swing.GroupLayout TituloLayout = new javax.swing.GroupLayout(Titulo);
        Titulo.setLayout(TituloLayout);
        TituloLayout.setHorizontalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TituloLayout.setVerticalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TituloLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    
    private void docentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docentesActionPerformed
        String docente, materiaNom, grupo, tipo, dia, hrInicio, hrFin;
        int materiaNum, fila, horasTotales = 0;
        
        //Obtén solo la clave del docente del comboBox
        docente = docentes.getSelectedItem().toString();
        docente = docente.substring(0, docente.indexOf(' '));
        //Abre las conexiones
        dao.abrirSSH();
        dao.abrirConexion();
        //Remueve las filas de la tabla
        removerFilas();
        try {
            //Lee los cursos que tengan al docente especificado
            cursos = dao.readProfesor("'" + docente + "'");
            
            //Recorre todo el arreglo y agrega los datos a la tabla
            for (CursoHorario curso : cursos) {
                materiaNum = curso.getCurso().getMateria().getClaveMateria();
                materiaNom = curso.getCurso().getMateria().getNom();
                grupo = curso.getCurso().getGrupo();
                tipo = curso.getCurso().getTipo();
                dia = curso.getHorario().getDia();
                hrInicio = curso.getHorario().getHrInicio().toString();
                hrFin = curso.getHorario().getHrFin().toString();
                
                //Agrega cada registro a la fila
                DefaultTableModel modelo = (DefaultTableModel) datosDocente.getModel();
                fila = modelo.getRowCount();
                if (fila == 0) {
                    //Siempre agrega el primer registro como la primera fila
                    horasTotales += agregarFila(materiaNum + " " + materiaNom,
                            grupo, tipo, dia, hrInicio, hrFin, modelo);
                } else {
                    fila--;
                    /* Si el registro actual es del mismo curso que el registro anterior
                       pero con un dia y hora diferente, agrega la hora a la columna del
                       día indicado del renglón del registro anterior.
                       Como puede haber varios profesores con el mismo nombre completo,
                       es necesario compararlos por sus números de empleado.
                    */
                    if (modelo.getValueAt(fila, MATERIA).equals(materiaNum + " " + materiaNom) &&
                            modelo.getValueAt(fila, GRUPO).equals(grupo) &&
                            modelo.getValueAt(fila, TIPO).equals(tipo)) {
                        horasTotales += actualizarFila(dia, hrInicio, hrFin, modelo);
                    } else {
                        horasTotales += agregarFila(materiaNum + " " + materiaNom,
                                grupo, tipo, dia, hrInicio, hrFin, modelo);
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
            this.horasTotales.setText("" + horasTotales);
        }
    }//GEN-LAST:event_docentesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrarVentana();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Agrega la fila a la tabla
     *
     * @param materia La clave y nombre de la materia que se imparte en el curso
     * @param grupo El grupo del curso impartido
     * @param tipo El tipo del curso impartido
     * @param dia El dia en el que se imparte el curso
     * @param hrInicio La hora de inicio del curso
     * @param hrFin La hora de finalización del curso
     * @param modelo La tabla en la que se agregará la fila
     * @return Las horas impartidas en el horario
     */
    private int agregarFila(String materia, String grupo, String tipo, String dia,
                                String hrInicio, String hrFin, DefaultTableModel modelo) {
        modelo.addRow(new Object[]{materia, grupo, tipo, "", "", "", "", "", "", 0});
        return actualizarFila(dia, hrInicio, hrFin, modelo);
    }
    
    /**
     * Actualiza la fila más reciente de la tabla
     *
     * @param dia El dia en el que se imparte el curso
     * @param hrInicio La hora de inicio del curso
     * @param hrFin La hora de finalización del curso
     * @param modelo La tabla cuya fila se actualizará
     * @return Las horas impartidas en el horario
     */
    private int actualizarFila(String dia, String hrInicio, String hrFin,
            DefaultTableModel modelo) {
        //Quita los segundos de las horas, ej: 08:00:00 -> 08:00
        hrInicio = hrInicio.substring(0, hrInicio.length()-3);
        hrFin = hrFin.substring(0, hrFin.length()-3);
        //Consigue la diferencia de horas
        int horas = Integer.parseInt(hrFin.substring(0, hrFin.indexOf(':'))) -
                Integer.parseInt(hrInicio.substring(0, hrInicio.indexOf(':')));
        
        switch (dia) {
            case "LUNES":
                //modelo.getValueAt(modelo.getRowCount()-1, LUNES);
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
        //Suma las horas de diferencia al total de horas impartidas del curso
        modelo.setValueAt((int)(modelo.getValueAt(modelo.getRowCount()-1, TOTAL_HORAS)) + horas,
                modelo.getRowCount()-1, TOTAL_HORAS);
        return horas;
    }
    
    //Agrega los docentes registrados a la lista de docentes
    private void construyeLista() {
        /* Desactiva el listener temporalmente para evitar que corra el evento
           al agregar nuevos objetos al comboBox
        */
        docentes.removeActionListener(docentes.getActionListeners()[0]);
        //Abre las conexiones
        ProfesorDAO dao = new ProfesorDAO();
        dao.abrirSSH();
        dao.abrirConexion();
        //Consigue la lista
        try {
            ArrayList<Profesor> listaDocentes = dao.readAll();
            //Recorre el arreglo y agrega cada materia a la lista
            for (Profesor docente : listaDocentes) {
                docentes.addItem(docente.getNumEmpleado() + " " + docente.getNom());
            }
        } catch (Exception e) {
            //Mensaje de error
            JOptionPane.showMessageDialog(this, "No se pudo conseguir la lista de "
                    + "docentes registrados\n" + e.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            //Cierra la conexión
            dao.cerrarSSH();
        }
        //Reactiva el listener
        docentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docentesActionPerformed(evt);
            }
        });
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
        DefaultTableModel modelo = (DefaultTableModel) datosDocente.getModel();
        modelo.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Titulo;
    private javax.swing.JPanel consulta;
    private javax.swing.JTable datosDocente;
    private javax.swing.JComboBox<String> docentes;
    private javax.swing.JLabel horasTotales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton regresar;
    private javax.swing.JLabel semestreActual;
    // End of variables declaration//GEN-END:variables
}
