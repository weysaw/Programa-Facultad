package programafacultad;

import conexion.Profesor;
import conexion.ProfesorDAO;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * Modifica el maestro de la base de datos que se indique
 * 
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo
 * @version 11.12.2020
 */
public class ModificarMaestro extends javax.swing.JFrame {

    private final Principal principal;
    private ArrayList<Profesor> profesor;
    private String numEmpleado;

    /**
     * Constructor de la clase 
     */
    public ModificarMaestro(Principal principal) {
        try {
            setIconImage(ImageIO.read(getClass().getResource("/graficos/uabc.png"))); //Icono del programa
        } catch (IOException ex) {
            Logger.getLogger(ModificarMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);
        profesor = new ArrayList();
        MensajeEspera mensaje = new MensajeEspera(principal) {
            @Override
            public void accion(Component cmp) {
                informacion(cmp);
            }
        };
        mensaje.mostrarMensaje();
        numDocente.setSelectedIndex(-1);
    }

    /* Método que trae la información de los profesores para
      los comboBox correspondientes*/
    public final void informacion(Component cmp) {
        ProfesorDAO profeDAO = new ProfesorDAO();
        profeDAO.abrirSSH();
        profeDAO.abrirConexion();
        try {
            profesor = new ArrayList();
            profesor = profeDAO.readAllOrdered();
            for (int i = 0; i < profesor.size(); i++) {
                numDocente.addItem(profesor.get(i).getNumEmpleado() + " " + profesor.get(i).getNom());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cmp, "ERROR\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            profeDAO.cerrarSSH();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipo = new javax.swing.ButtonGroup();
        modificar = new javax.swing.JPanel();
        botonModificar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numDocente = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        asignatura = new javax.swing.JRadioButton();
        completo = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();
        Titulo = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Docente");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        botonModificar.setText("Modificar");
        botonModificar.setEnabled(false);
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Modificar/Baja Docente");

        jLabel2.setText("Seleccione el número de empleado a modificar:");

        numDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocenteActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre:");

        tipo.add(asignatura);
        asignatura.setSelected(true);
        asignatura.setText("Asignatura");

        tipo.add(completo);
        completo.setText("Tiempo Completo");

        jLabel4.setText("Tipo:");

        eliminar.setText("Eliminar");
        eliminar.setEnabled(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modificarLayout = new javax.swing.GroupLayout(modificar);
        modificar.setLayout(modificarLayout);
        modificarLayout.setHorizontalGroup(
            modificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(modificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numDocente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre)
                    .addGroup(modificarLayout.createSequentialGroup()
                        .addGroup(modificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(modificarLayout.createSequentialGroup()
                                .addComponent(completo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(modificarLayout.createSequentialGroup()
                        .addComponent(eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regresar)))
                .addContainerGap())
        );
        modificarLayout.setVerticalGroup(
            modificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(completo)
                    .addComponent(asignatura))
                .addGap(20, 20, 20)
                .addGroup(modificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar)
                    .addComponent(botonModificar)
                    .addComponent(regresar))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        cerrrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if (nombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduzca un nombre para el docente.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int reply = JOptionPane.showConfirmDialog(this, "¿Seguro que desea modificar esta información?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MensajeEspera mensaje = new MensajeEspera(this) {
                @Override
                public void accion(Component cmp) {
                ProfesorDAO profeDAO = new ProfesorDAO();
                profeDAO.abrirSSH();
                profeDAO.abrirConexion();
                try {
                    //Actualiza al docente
                    Profesor empleado = new Profesor(numEmpleado, nombre.getText(), completo.isSelected());
                    profeDAO.update(empleado);
                    //Hace la modificación también en el ArrayList de docentes.
                    int i = numDocente.getSelectedIndex();
                    profesor.set(i, empleado);
                    //Actualiza el combobox
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                    for (Profesor p : profesor) {
                        model.addElement(p.getNumEmpleado() + " " + p.getNom());
                    }
                    numDocente.setModel(model);
                    numDocente.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(cmp, "Se ha modificado con exito", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
                    JOptionPane.showMessageDialog(cmp, "Ya existe un Maestro registrada con este número de empleado\n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) { //Error en general
                    JOptionPane.showMessageDialog(cmp, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally { //Cierra el ssh
                    profeDAO.cerrarSSH();
                }
                }
            };
            mensaje.mostrarMensaje();
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrrarVentana();
    }//GEN-LAST:event_formWindowClosing

    private void numDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocenteActionPerformed
        if (numDocente.getSelectedIndex() == -1) {
            nombre.setText("");
            eliminar.setEnabled(false);
            botonModificar.setEnabled(false);
            return;
        } else {
            eliminar.setEnabled(true);
            botonModificar.setEnabled(true);
        }
        // Se llenan los jText con la informacion del numero de empleado
        String numeroDocente, num;
        num = numDocente.getSelectedItem().toString();
        num = num.substring(0, num.indexOf(" "));
        for (int i = 0; i < profesor.size(); i++) {
            numeroDocente = profesor.get(i).getNumEmpleado();
            if (num.equals(numeroDocente)) {
                numEmpleado = profesor.get(i).getNumEmpleado();
                nombre.setText(profesor.get(i).getNom());
                completo.setSelected(profesor.get(i).isEsTiempoCompleto());
                asignatura.setSelected(!profesor.get(i).isEsTiempoCompleto());
            }
        }
    }//GEN-LAST:event_numDocenteActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int reply = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar a este docente?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            MensajeEspera mensaje = new MensajeEspera(this) {
                @Override
                public void accion(Component cmp) {
                ProfesorDAO profeDAO = new ProfesorDAO();
                profeDAO.abrirSSH();
                profeDAO.abrirConexion();
                try {
                    //Obtiene el objeto Profesor que eliminar
                    int i = numDocente.getSelectedIndex();
                    Profesor prof = profesor.get(i);
                    profeDAO.delete(prof);
                    //Elimina al profesor del ArrayList también
                    profesor.remove(prof);
                    //También lo elimina del combobox
                    numDocente.removeItem(prof.getNumEmpleado() + " " + prof.getNom());
                    numDocente.setSelectedIndex(-1);
                    JOptionPane.showMessageDialog(cmp, "Eliminado exitosamente.", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLIntegrityConstraintViolationException ex) {
                    JOptionPane.showMessageDialog(cmp, "Este docente está impartiendo un curso, no puede ser eliminado\n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) { //Error en general
                    JOptionPane.showMessageDialog(cmp, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally { //Cierra el ssh
                    profeDAO.cerrarSSH();
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
    private javax.swing.JRadioButton asignatura;
    private javax.swing.JButton botonModificar;
    private javax.swing.JRadioButton completo;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel modificar;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<String> numDocente;
    private javax.swing.JButton regresar;
    private javax.swing.ButtonGroup tipo;
    // End of variables declaration//GEN-END:variables
}
