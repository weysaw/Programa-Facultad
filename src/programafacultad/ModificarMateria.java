package programafacultad;

import conexion.Materia;
import conexion.MateriaDAO;
import java.awt.Component;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * Modifica la materia
 *
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo
 * @version 16.12.2020
 */
public class ModificarMateria extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<Materia> materiaDAO;
    private int numMateria;

    /**
     * Creates new form ModificarMateria
     */
    public ModificarMateria(Principal principal) {
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        materiaDAO = new ArrayList();
        MensajeEspera mensaje = new MensajeEspera(principal) {
            @Override
            public void accion(Component cmp) {
                informacion(cmp);
            }
        };
        mensaje.mostrarMensaje();
        materias.setSelectedIndex(-1);
    }

    /* Método que trae la información de las materias para
      los comboBox correspondientes*/
    public final void informacion(Component cmp) {
        MateriaDAO materia = new MateriaDAO();
        materia.abrirSSH();
        materia.abrirConexion();
        try {
            materiaDAO = new ArrayList();
            materiaDAO = materia.readAll();
            for (int i = 0; i < materiaDAO.size(); i++) {
                materias.addItem(materiaDAO.get(i).getClaveMateria() + " " + materiaDAO.get(i).getNom());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cmp, "ERROR\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            materia.cerrarSSH();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        regresar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nomMateria = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        materias = new javax.swing.JComboBox<>();
        eliminar = new javax.swing.JButton();
        Titulo = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar materia");
        setResizable(false);
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

        botonModificar.setText("Modificar");
        botonModificar.setEnabled(false);
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Modificar/Baja Materia");

        jLabel2.setText("Seleccione la materia a modificar:");

        jLabel3.setText("Nombre de la Materia:");

        materias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiasActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(materias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomMateria)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(eliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(regresar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 82, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(materias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regresar)
                    .addComponent(botonModificar)
                    .addComponent(eliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addContainerGap(17, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void materiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiasActionPerformed
        if (materias.getSelectedIndex() == -1) {
            nomMateria.setText("");
            eliminar.setEnabled(false);
            botonModificar.setEnabled(false);
            return;
        } else {
            eliminar.setEnabled(true);
            botonModificar.setEnabled(true);
        }
        // Se llenan los jText con la informacion de la materia
        String nombreMateria;
        for (int i = 0; i < materiaDAO.size(); i++) {
            nombreMateria = materiaDAO.get(i).getClaveMateria() + " " + materiaDAO.get(i).getNom();
            if (materias.getSelectedItem().equals(nombreMateria)) {
                numMateria = materiaDAO.get(i).getClaveMateria();
                nomMateria.setText(materiaDAO.get(i).getNom());
            }
        }
    }//GEN-LAST:event_materiasActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        cerrrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrrarVentana();
    }//GEN-LAST:event_formWindowClosing

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if (nomMateria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduzca un nombre", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int reply = JOptionPane.showConfirmDialog(null, "¿Seguro que desea registrar esta información?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MensajeEspera mensaje = new MensajeEspera(this) {
                @Override
                public void accion(Component cmp) {
                MateriaDAO matDAO = new MateriaDAO();
                matDAO.abrirSSH();
                matDAO.abrirConexion();
                try {
                    Materia materia = new Materia(numMateria, nomMateria.getText());
                    matDAO.update(materia);
                    //Actualiza la materia en el ArrayList
                    int i = materias.getSelectedIndex();
                    materiaDAO.set(i, materia);
                    //Actualiza el combobox
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                    for (Materia m : materiaDAO) {
                        model.addElement(m.getClaveMateria() + " " + m.getNom());
                    }
                    materias.setModel(model);
                    materias.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(cmp, "Se ha modificado con exito", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
                    JOptionPane.showMessageDialog(cmp, "Ya existe una Materia registrada con este número de Materia\n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) { //Error en general
                    JOptionPane.showMessageDialog(cmp, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally { //Cierra el ssh
                    matDAO.cerrarSSH();
                }
                }
            };
            mensaje.mostrarMensaje();
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int reply = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar a esta materia?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MensajeEspera mensaje = new MensajeEspera(this) {
                @Override
                public void accion(Component cmp) {
                MateriaDAO matDAO = new MateriaDAO();
                matDAO.abrirSSH();
                matDAO.abrirConexion();
                try {
                    //Obtiene la materia a eliminar
                    int i = materias.getSelectedIndex();
                    Materia materia = materiaDAO.get(i);
                    matDAO.delete(materia);
                    //Elimina la materia del ArrayList
                    materiaDAO.remove(materia);
                    //La elimina también del combobox
                    materias.removeItem(materia.getClaveMateria() + " " + materia.getNom());
                    materias.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(cmp, "Eliminado exitosamente.", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) { //Error en general
                    JOptionPane.showMessageDialog(cmp, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally { //Cierra el ssh
                    matDAO.cerrarSSH();
                }
                }
            };
            mensaje.mostrarMensaje();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrrarVentana() {
        principal.setVisible(true);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Titulo;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> materias;
    private javax.swing.JTextField nomMateria;
    private javax.swing.JButton regresar;
    // End of variables declaration//GEN-END:variables
}
