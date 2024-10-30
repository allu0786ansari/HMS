package officemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Module2 extends javax.swing.JFrame {

    private Image backgroundImage;

    public Module2() {
        initComponents();
        backgroundImage = new ImageIcon("src/officemanagementsystem/background.jpg").getImage();
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
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

        // Title Panel
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);

        JLabel jLabel1 = new JLabel("DOCTOR AVAILABILITY");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 24));
        jLabel1.setForeground(Color.BLACK);

        // Add title components to titlePanel
        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.anchor = GridBagConstraints.CENTER;
        titleGbc.insets = new Insets(10, 10, 10, 10);

        titleGbc.gridy = 0;
        titlePanel.add(titleLabel, titleGbc);

        titleGbc.gridy = 1;
        titlePanel.add(jLabel1, titleGbc);

        // Main layout components
        JLabel jLabel2 = new JLabel("Select your Doctor:");
        jLabel2.setForeground(Color.BLACK);

        JLabel jLabel3 = new JLabel("Doctor Appointment Time:");
        jLabel3.setForeground(Color.BLACK);

        JComboBox<String> jComboBox1 = new JComboBox<>(new String[]{
                "Dr.David (10:00am)", "Dr.Vivek (11:00am)", "Dr.Thomson (12:00pm)",
                "Dr.Tina (1:00pm)", "Dr.Hassan (2:00pm)"
        });

        JLabel jLabel4 = new JLabel("Appointment Fee:");
        jLabel4.setForeground(Color.BLACK);

        JComboBox<String> jComboBox2 = new JComboBox<>(new String[]{
                "Dr.David (100USD$)", "Dr.Vivek (200USD$)", "Dr.Thomson (300USD$)",
                "Dr.Tina (400USD$)", "Dr.Hassan (500USD$)"
        });

        JLabel jLabel5 = new JLabel("Payment Option:");
        jLabel5.setForeground(Color.BLACK);

        JComboBox<String> jComboBox3 = new JComboBox<>(new String[]{"cash", "credit card", "Already paid"});

        JTextField dt = new JTextField(20);

        JButton jButton1 = new JButton("OK");
        jButton1.setBackground(new Color(30, 144, 255));
        jButton1.setForeground(Color.WHITE);
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(jComboBox1, dt, jComboBox2, jComboBox3);
            }
        });

        JLabel jLabel6 = new JLabel("GO BACK");
        jLabel6.setFont(new Font("Arial", Font.BOLD, 16));
        jLabel6.setForeground(new Color(255, 99, 71));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        // Layout setup using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add titlePanel at the top of mainPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titlePanel, gbc);

        // Reset grid width for other components
        gbc.gridwidth = 1;

        // Doctor selection
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(jLabel2, gbc);

        gbc.gridx = 1;
        mainPanel.add(jComboBox1, gbc);

        // Appointment Time
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(jLabel3, gbc);

        gbc.gridx = 1;
        mainPanel.add(dt, gbc);

        // Appointment Fee
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(jLabel4, gbc);

        gbc.gridx = 1;
        mainPanel.add(jComboBox2, gbc);

        // Payment Option
        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(jLabel5, gbc);

        gbc.gridx = 1;
        mainPanel.add(jComboBox3, gbc);

        // OK Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        mainPanel.add(jButton1, gbc);

        // GO BACK Label centered at the bottom
        gbc.gridy++;
        mainPanel.add(jLabel6, gbc);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    private void jButton1ActionPerformed(JComboBox<String> jComboBox1, JTextField dt, JComboBox<String> jComboBox2, JComboBox<String> jComboBox3) {
        String doctorName = jComboBox1.getSelectedItem().toString();
        String appointmentTime = dt.getText();
        String appointmentFee = jComboBox2.getSelectedItem().toString();
        String paymentOption = jComboBox3.getSelectedItem().toString();

        boolean isSaved = DatabaseManager.saveDoctorAvailability(doctorName, appointmentTime,
                Double.parseDouble(appointmentFee.replaceAll("[^0-9.]", "")),
                paymentOption);

        if (isSaved) {
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error saving data. Please try again.");
        }
    }

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {
        MenuPage obj = new MenuPage();
        obj.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Module2().setVisible(true));
    }
}
