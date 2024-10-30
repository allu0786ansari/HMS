package officemanagementsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Module1 extends JFrame {

    private Image backgroundImage;

    public Module1() {
        initComponents();
        showDate();
        showTime();
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Load the background image
        backgroundImage = new ImageIcon("src/officemanagementsystem/background.jpg").getImage();
    }

    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        datelab.setText(s.format(d));
    }

    void showTime() {
        new Timer(0, e -> {
            Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            timelab.setText(s.format(d));
        }).start();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        String pname = pn.getText();
        String fname = fn.getText();
        String cnic = CNIC.getText();
        String illness = illnessArea.getText(); // Use the text area for illness
        String doctorName = doctor.getSelectedItem().toString();
        String date = datelab.getText();
        String time = timelab.getText();

        // Insert data into the database
        try (Connection conn = DatabaseManager.connect()) {
            String sql = "INSERT INTO patients (patient_name, father_name, cnic, illness, doctor, date, time) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pname);
                pstmt.setString(2, fname);
                pstmt.setString(3, cnic);
                pstmt.setString(4, illness); // This now takes input from the text area
                pstmt.setString(5, doctorName);
                pstmt.setString(6, date);
                pstmt.setString(7, time);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Patient data has been submitted successfully.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {
        MenuPage obj = new MenuPage();
        obj.setVisible(true);
        dispose();
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

        // Add the Hospital Management System title
        JLabel systemTitleLabel = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        systemTitleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        systemTitleLabel.setForeground(Color.BLACK);

        // Title Label
        JLabel titleLabel = new JLabel("PATIENT FORM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);

        JLabel subtitleLabel = new JLabel("Enter your details");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.BLACK);

        // Labels and Text Fields
        JLabel patientLabel = new JLabel("Patient Name:");
        JLabel fatherLabel = new JLabel("Father name:");
        JLabel cnicLabel = new JLabel("CNIC Number:");
        JLabel illnessLabel = new JLabel("Patient Illness:");
        JLabel doctorLabel = new JLabel("Choose Doctor:");
        patientLabel.setForeground(Color.BLACK);
        fatherLabel.setForeground(Color.BLACK);
        cnicLabel.setForeground(Color.BLACK);
        illnessLabel.setForeground(Color.BLACK);
        doctorLabel.setForeground(Color.BLACK);

        pn = new JTextField(20);
        fn = new JTextField(20);
        CNIC = new JTextField(20);

        // Create a JTextArea for entering the patient's illness
        illnessArea = new JTextArea(3, 50); // 3 rows and 20 columns
        illnessArea.setLineWrap(true);
        illnessArea.setWrapStyleWord(true);
        JScrollPane illnessScrollPane = new JScrollPane(illnessArea); // Add scroll pane for better UI
        illnessScrollPane.setPreferredSize(new Dimension(200, 60)); // Set preferred size for the scroll pane

        doctor = new JComboBox<>(new String[]{"Dr. David", "Dr. Thomson", "Dr. Vivek", "Dr. Hassan", "Dr. Tina"});

        datelab = new JLabel("Date:");
        timelab = new JLabel("Time:");
        datelab.setForeground(Color.BLACK);
        timelab.setForeground(Color.BLACK);

        jButton1 = new JButton("Submit");
        jButton1.setBackground(new Color(30, 144, 255));
        jButton1.setForeground(Color.WHITE);
        jButton1.setPreferredSize(new Dimension(120, 30));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel8 = new JLabel("GO BACK");
        jLabel8.setFont(new Font("Arial", Font.PLAIN, 16));
        jLabel8.setForeground(new Color(255, 99, 71));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(systemTitleLabel, gbc); // Add system title

        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(subtitleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(patientLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(pn, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(fatherLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(fn, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(cnicLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(CNIC, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(illnessLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(illnessScrollPane, gbc); // Add the scroll pane with the text area

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(doctorLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(doctor, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        mainPanel.add(datelab, gbc);
        gbc.gridx++;
        mainPanel.add(timelab, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        mainPanel.add(jButton1, gbc);

        gbc.gridy++;
        mainPanel.add(jLabel8, gbc);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Module1().setVisible(true));
    }

    private JTextField CNIC, fn, pn;
    private JComboBox<String> doctor;
    private JTextArea illnessArea; // Declare the JTextArea for illness input
    private JLabel datelab, timelab;
    private JButton jButton1;
    private JLabel jLabel8;
}
