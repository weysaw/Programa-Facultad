package programafacultad;

import conexion.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.awt.Component;

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
        MensajeEspera mensaje = new MensajeEspera(principal) {
            @Override
            public void accion(Component cmp) {
                construyeLista();
            }
        };
        mensaje.mostrarMensaje();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Titulo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        consultas = new javax.swing.JPanel();
        semestreActual = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        materias = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        datosMaterias = new javax.swing.JTable();
        regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta por materia");
        setMinimumSize(new java.awt.Dimension(620, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Facultad de Ingeniería Campus Mexicali");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graficos/uabc.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Planta Académica FIM");

        javax.swing.GroupLayout TituloLayout = new javax.swing.GroupLayout(Titulo);
        Titulo.setLayout(TituloLayout);
        TituloLayout.setHorizontalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        TituloLayout.setVerticalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TituloLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        semestreActual.setText("20XX-X");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Materias Facultad de Ingeniería");

        jLabel3.setText("Semestre Actual: ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Materia: ");

        materias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiasActionPerformed(evt);
            }
        });

        datosMaterias.setAutoCreateRowSorter(true);
        datosMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Docente", "Grupo", "Tipo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datosMaterias.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(datosMaterias);
        if (datosMaterias.getColumnModel().getColumnCount() > 0) {
            datosMaterias.getColumnModel().getColumn(0).setPreferredWidth(250);
        }

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout consultasLayout = new javax.swing.GroupLayout(consultas);
        consultas.setLayout(consultasLayout);
        consultasLayout.setHorizontalGroup(
            consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(regresar)
                .addContainerGap())
            .addGroup(consultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultasLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(consultasLayout.createSequentialGroup()
                        .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(consultasLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(consultasLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(materias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        consultasLayout.setVerticalGroup(
            consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(semestreActual, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regresar)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(consultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        MensajeEspera mensaje = new MensajeEspera(this) {
            @Override
            public void accion(Component cmp) {
                String materia, docenteNum, docenteNom, grupo, tipo, dia, hrInicio, hrFin;
                int fila;

                //Obtén solo la clave de la materia del comboBox
                materia = materias.getSelectedItem().toString();
                materia = materia.substring(0, materia.indexOf(' '));
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
                        docenteNum = curso.getCurso().getProfesor().getNumEmpleado();
                        docenteNom = curso.getCurso().getProfesor().getNom();
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
                            agregarFila(docenteNum + " " + docenteNom, grupo, tipo, dia,
                                    hrInicio, hrFin, modelo);
                        } else {
                            fila--;
                            /* Si el registro actual es del mismo curso que el registro anterior
                       pero con un dia y hora diferente, agrega la hora a la columna del
                       día indicado del renglón del registro anterior.
                       Como puede haber varios profesores con el mismo nombre completo,
                       es necesario compararlos por sus números de empleado.
                             */
                            if (modelo.getValueAt(fila, DOCENTE).equals(docenteNum + " " + docenteNom)
                                    && modelo.getValueAt(fila, GRUPO).equals(grupo)
                                    && modelo.getValueAt(fila, TIPO).equals(tipo)) {
                                actualizarFila(dia, hrInicio, hrFin, modelo);
                            } else {
                                agregarFila(docenteNum + " " + docenteNom, grupo, tipo,
                                        dia, hrInicio, hrFin, modelo);
                            }
                        }
                    }
                } catch (Exception e) {
                    //Mensaje de error
                    JOptionPane.showMessageDialog(cmp, "No se pudo leer la base de datos\n" + e.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    //Cierra la conexión
                    dao.cerrarSSH();
                }
            }
        };
        mensaje.mostrarMensaje();


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
                materias.addItem(materia.getClaveMateria() + " " + materia.getNom());
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
     * @param docente La clave y nombre del docente que imparte el curso
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
        hrInicio = hrInicio.substring(0, hrInicio.length() - 3);
        hrFin = hrFin.substring(0, hrFin.length() - 3);

        switch (dia) {
            case "LUNES":
                modelo.setValueAt(hrInicio + "-" + hrFin, modelo.getRowCount() - 1, LUNES);
                break;
            case "MARTES":
                modelo.setValueAt(hrInicio + "-" + hrFin, modelo.getRowCount() - 1, MARTES);
                break;
            case "MIERCOLES":
                modelo.setValueAt(hrInicio + "-" + hrFin, modelo.getRowCount() - 1, MIERCOLES);
                break;
            case "JUEVES":
                modelo.setValueAt(hrInicio + "-" + hrFin, modelo.getRowCount() - 1, JUEVES);
                break;
            case "VIERNES":
                modelo.setValueAt(hrInicio + "-" + hrFin, modelo.getRowCount() - 1, VIERNES);
                break;
            case "SABADO":
                modelo.setValueAt(hrInicio + "-" + hrFin, modelo.getRowCount() - 1, SABADO);
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
    private javax.swing.JPanel Titulo;
    private javax.swing.JPanel consultas;
    private javax.swing.JTable datosMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> materias;
    private javax.swing.JButton regresar;
    private javax.swing.JLabel semestreActual;
    // End of variables declaration//GEN-END:variables
}
