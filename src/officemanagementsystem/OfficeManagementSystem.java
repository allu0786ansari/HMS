package officemanagementsystem;

import javax.swing.SwingUtilities;

public class OfficeManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Use SwingUtilities to ensure that GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the LoginPage
                new LoginPage().setVisible(true);
            }
        });
    }
}
