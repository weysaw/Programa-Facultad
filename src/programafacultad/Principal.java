package programafacultad;

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
        setLocationRelativeTo(null);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ConsultaHorario = new javax.swing.JButton();
        ConsultaMateria = new javax.swing.JButton();
        ConsultaMaestro = new javax.swing.JButton();
        ConsultaHoras = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Programa Consultas");

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Facultad De Ingenieria Campus Mexicali");

        ConsultaHorario.setBackground(new java.awt.Color(255, 255, 102));
        ConsultaHorario.setText("Consulta por Horario");
        ConsultaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaHorarioActionPerformed(evt);
            }
        });

        ConsultaMateria.setBackground(new java.awt.Color(255, 255, 102));
        ConsultaMateria.setText("Consulta por Materia");
        ConsultaMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaMateriaActionPerformed(evt);
            }
        });

        ConsultaMaestro.setBackground(new java.awt.Color(255, 255, 102));
        ConsultaMaestro.setText("Consulta por Maestro");
        ConsultaMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaMaestroActionPerformed(evt);
            }
        });

        ConsultaHoras.setBackground(new java.awt.Color(255, 255, 102));
        ConsultaHoras.setText("Numeros");
        ConsultaHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaHorasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ConsultaHorario)
                    .addComponent(ConsultaMaestro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ConsultaMateria)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(ConsultaHoras)))
                .addGap(98, 98, 98))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConsultaMateria)
                    .addComponent(ConsultaHorario))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConsultaHoras)
                    .addComponent(ConsultaMaestro))
                .addContainerGap(76, Short.MAX_VALUE))
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

    private void ConsultaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaHorarioActionPerformed
        frmHorario horario = new frmHorario(this);
        horario.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_ConsultaHorarioActionPerformed

    private void ConsultaMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaMaestroActionPerformed
         frmMaestro maestro = new frmMaestro(this);
         maestro.setVisible(true);
         setVisible(false);
    }//GEN-LAST:event_ConsultaMaestroActionPerformed

    private void ConsultaMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaMateriaActionPerformed
        frmMateria materia = new frmMateria(this);
        materia.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_ConsultaMateriaActionPerformed

    private void ConsultaHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaHorasActionPerformed
        frmHoras hora = new frmHoras(this, true);
        hora.setVisible(true);
    }//GEN-LAST:event_ConsultaHorasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConsultaHorario;
    private javax.swing.JButton ConsultaHoras;
    private javax.swing.JButton ConsultaMaestro;
    private javax.swing.JButton ConsultaMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
