package officemanagementsystem;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends javax.swing.JFrame {

    private Image backgroundImage;
    private JLabel jLabelLogout, jLabelTitle, jLabelSubTitle;
    private JButton jButtonPatientForm, jButtonDoctorAvailability;

    public MenuPage() {
        initComponents();
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Load the background image
        backgroundImage = new ImageIcon("src/officemanagementsystem/background.jpg").getImage();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        // Title and subtitle
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        jLabelTitle = new JLabel("Hospital Management System");
        jLabelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        jLabelTitle.setForeground(Color.BLACK);
        jLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        jLabelSubTitle = new JLabel("Menu Page");
        jLabelSubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        jLabelSubTitle.setForeground(Color.BLACK);
        jLabelSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(jLabelTitle);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(jLabelSubTitle);

        // Logout label
        jLabelLogout = new JLabel("LOGOUT");
        jLabelLogout.setFont(new Font("Arial", Font.BOLD, 14));
        jLabelLogout.setForeground(new Color(255, 69, 0));
        jLabelLogout.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogoutMouseClicked(evt);
            }
        });

        // Buttons
        jButtonPatientForm = new JButton("Go to the Patient Form");
        jButtonPatientForm.setPreferredSize(new Dimension(400, 50));
        jButtonPatientForm.setBackground(new Color(30, 144, 255));
        jButtonPatientForm.setForeground(Color.BLACK);
        jButtonPatientForm.addActionListener(evt -> jButtonPatientFormActionPerformed());

        jButtonDoctorAvailability = new JButton("Doctor Availability");
        jButtonDoctorAvailability.setPreferredSize(new Dimension(400, 50));
        jButtonDoctorAvailability.setBackground(new Color(34, 139, 34));
        jButtonDoctorAvailability.setForeground(Color.BLACK);
        jButtonDoctorAvailability.addActionListener(evt -> jButtonDoctorAvailabilityActionPerformed());

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        mainPanel.add(jLabelLogout, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titlePanel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(jButtonPatientForm, gbc);

        gbc.gridy = 3;
        mainPanel.add(jButtonDoctorAvailability, gbc);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        pack();
    }

    private void jButtonPatientFormActionPerformed() {
        Module1 obj = new Module1();
        obj.setVisible(true);
        dispose();
    }

    private void jButtonDoctorAvailabilityActionPerformed() {
        Module2 obj = new Module2();
        obj.setVisible(true);
        dispose();
    }

    private void jLabelLogoutMouseClicked(java.awt.event.MouseEvent evt) {
        LoginPage obj = new LoginPage();
        obj.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new MenuPage().setVisible(true));
    }
}
