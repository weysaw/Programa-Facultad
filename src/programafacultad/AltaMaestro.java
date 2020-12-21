package programafacultad;

import javax.swing.JOptionPane;
import conexion.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Da de alta maestros
 *
 * @author Leslie Vidal, Ornelas Munguía Axel Leonardo
 * @version 04.12.2020
 */
public class AltaMaestro extends javax.swing.JFrame {

    private final Principal principal;

    /**
     * Constructor de la clase
     */
    public AltaMaestro(Principal principal) {
        try {
            setIconImage(ImageIO.read(getClass().getResource("/graficos/uabc.png"))); //Icono del programa
        } catch (IOException ex) {
            Logger.getLogger(ModificarMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.principal = principal;
        setLocationRelativeTo(principal);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoProfe = new javax.swing.ButtonGroup();
        Titulo = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Alta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numeroEmpleado = new javax.swing.JTextField();
        nombreDocente = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        completo = new javax.swing.JRadioButton();
        asignatura = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        apellidoM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        apellidoP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alta de docente");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Alta de Docentes");

        jLabel2.setText("Número de Empleado:");

        jLabel3.setText("Nombre(s):");

        numeroEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroEmpleadoKeyTyped(evt);
            }
        });

        nombreDocente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreDocenteKeyTyped(evt);
            }
        });

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

        tipoProfe.add(completo);
        completo.setSelected(true);
        completo.setText("Tiempo Completo");

        tipoProfe.add(asignatura);
        asignatura.setText("Asignatura");

        jLabel4.setText("Tipo:");

        jLabel5.setText("Apellido materno:");

        jLabel6.setText("Apellido paterno:");

        javax.swing.GroupLayout AltaLayout = new javax.swing.GroupLayout(Alta);
        Alta.setLayout(AltaLayout);
        AltaLayout.setHorizontalGroup(
            AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreDocente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AltaLayout.createSequentialGroup()
                        .addComponent(registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(regresar))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numeroEmpleado)
                    .addComponent(apellidoM)
                    .addComponent(apellidoP)
                    .addGroup(AltaLayout.createSequentialGroup()
                        .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(AltaLayout.createSequentialGroup()
                                .addComponent(completo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(asignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        AltaLayout.setVerticalGroup(
            AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AltaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numeroEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(completo)
                    .addComponent(asignatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrar)
                    .addComponent(regresar))
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
                    .addComponent(Alta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Alta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        if (numeroEmpleado.getText().isEmpty() || nombreDocente.getText().isEmpty() || (apellidoM.getText().isEmpty() && apellidoP.getText().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Debe introducir número de empleado, nombre y por lo menos un apellido.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //Se crea el objeto que se registra
        ProfesorDAO profeDAO = new ProfesorDAO();
        profeDAO.abrirSSH();
        profeDAO.abrirConexion();
        //Agrega el profesor agarrando sus datos.
        try {
            String numEmp = numeroEmpleado.getText();
            String apellidos = apellidoP.getText();
            String nombres = nombreDocente.getText();
            //Verifica si están los dos apellidos, o sólo uno
            if (apellidos.isEmpty())
                apellidos = apellidoM.getText();
            else if (!(apellidoM.getText().isEmpty()))
                apellidos += " " + apellidoM.getText();

            Profesor profesor = new Profesor(numEmp,
                    apellidos + " " + nombres, completo.isSelected());
            profeDAO.append(profesor);
            JOptionPane.showMessageDialog(this, "Registrado con éxito.", "EXITO", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLIntegrityConstraintViolationException ex) { //Si hay error se los indica
            JOptionPane.showMessageDialog(this, "Ya existe un docente registrado con este número de Empleado\n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) { //Error en general
            JOptionPane.showMessageDialog(this, "ERROR \n" + ex.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally { //Cierra el ssh
            profeDAO.cerrarSSH();
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        //Regresa al menu principal
        cerrrarVentana();
    }//GEN-LAST:event_regresarActionPerformed

    private void numeroEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroEmpleadoKeyTyped
        char C = evt.getKeyChar();
        //Si teclea alguna espacio en blanco o letra le dice que es un error
        if (Character.isAlphabetic(C) || Character.isSpaceChar(C)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingrese solo numeros", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_numeroEmpleadoKeyTyped

    private void nombreDocenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreDocenteKeyTyped
        char C = evt.getKeyChar();
        //Si es un número el que se ingrese se lo indica
        if (Character.isDigit(C)) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingrese solo texto", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_nombreDocenteKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrrarVentana();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Cierra la ventana y muestra la principal
     */
    private void cerrrarVentana() {
        principal.setVisible(true);
        dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Alta;
    private javax.swing.JPanel Titulo;
    private javax.swing.JTextField apellidoM;
    private javax.swing.JTextField apellidoP;
    private javax.swing.JRadioButton asignatura;
    private javax.swing.JRadioButton completo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nombreDocente;
    private javax.swing.JTextField numeroEmpleado;
    private javax.swing.JButton registrar;
    private javax.swing.JButton regresar;
    private javax.swing.ButtonGroup tipoProfe;
    // End of variables declaration//GEN-END:variables
}
