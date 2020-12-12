package programafacultad;

import java.awt.Component;
import javax.swing.*;

/**
 * Muestra el mensaje de espera y realiza la acción que se le indique
 *
 * @author Ornelas Munguía Axel Leonardo
 * @version 10.12.2020
 */
public abstract class MensajeEspera {

    private final JDialog dialog;
    private final Component cmp;

    /**
     * Inicializa la ventana del mensaje
     *
     * @param cmp Es el componente en donde se muestra
     */
    public MensajeEspera(Component cmp) {
        this.cmp = cmp;
        JOptionPane mensajeEspera = new JOptionPane(new Cargando(), JOptionPane.PLAIN_MESSAGE,
                 JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        dialog = new JDialog();
        dialog.setTitle("Cargando...");
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setContentPane(mensajeEspera);
        dialog.setLocationRelativeTo(cmp);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
    }

    /**
     * Muestra el mensaje despues de realizar la acción cierra el mensaje
     *
     */
    public void mostrarMensaje() {
        SwingWorker<Void, Void> trabajo = new SwingWorker() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                accion(cmp);
                return null;
            }

            @Override
            protected void done() {
                dialog.dispose();
            }
        };
        trabajo.execute();
        dialog.setVisible(true);
    }

    /**
     * Es la accion que se realiza mientras esta el mensaje
     */
    public abstract void accion(Component cmp);

}
