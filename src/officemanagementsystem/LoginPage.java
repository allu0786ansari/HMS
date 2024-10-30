package officemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

    private JCheckBox showPasswordCheckBox, rememberMeCheckBox;
    private JButton forgotPasswordButton, registerButton;
    private Image backgroundImage;

    public LoginPage() {
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

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("Hospital Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setFont(new Font("Arial", Font.BOLD, 20));
        signInLabel.setForeground(Color.BLACK);
        signInLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(signInLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        mainPanel.add(titlePanel, gbc);

        jLabelUsername = new JLabel("Username:");
        jLabelUsername.setForeground(Color.BLACK);
        jLabelPassword = new JLabel("Password:");
        jLabelPassword.setForeground(Color.BLACK);

        textFieldUsername = new JTextField(25);
        passwordField = new JPasswordField(25);
        buttonLogin = new JButton("Login");
        registerButton = new JButton("Register");
        showPasswordCheckBox = new JCheckBox("Show Password");
        rememberMeCheckBox = new JCheckBox("Remember Me");
        forgotPasswordButton = new JButton("Forgot Password?");

        styleComponents();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(jLabelUsername, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(textFieldUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(jLabelPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(passwordField, gbc);

        // Panel to arrange "Show Password" and "Remember Me" checkboxes in a row
        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkBoxPanel.setOpaque(false);
        checkBoxPanel.add(showPasswordCheckBox);
        checkBoxPanel.add(rememberMeCheckBox);

        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(checkBoxPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(buttonLogin);
        buttonPanel.add(registerButton);
        mainPanel.add(buttonPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        mainPanel.add(forgotPasswordButton, gbc);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private void styleComponents() {
        buttonLogin.setBackground(new Color(30, 144, 255));
        buttonLogin.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        forgotPasswordButton.setForeground(new Color(255, 99, 71));
        showPasswordCheckBox.setForeground(Color.BLACK);
        rememberMeCheckBox.setForeground(Color.BLACK);

        // Increase button sizes
        buttonLogin.setPreferredSize(new Dimension(120, 30));
        registerButton.setPreferredSize(new Dimension(120, 30));

        buttonLogin.addActionListener(this::buttonLoginActionPerformed);
        showPasswordCheckBox.addActionListener(this::togglePasswordVisibility);
        forgotPasswordButton.addActionListener(this::forgotPasswordActionPerformed);
        registerButton.addActionListener(this::registerButtonActionPerformed);
    }

    private void buttonLoginActionPerformed(ActionEvent evt) {
        String username = textFieldUsername.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        if (DatabaseManager.validateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful");
            MenuPage obj = new MenuPage();
            obj.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username or password is incorrect");
        }
    }

    private void togglePasswordVisibility(ActionEvent evt) {
        passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? '\u0000' : '*');
    }

    private void forgotPasswordActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Password recovery feature coming soon!");
    }

    private void registerButtonActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Registration feature coming soon!");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }

    private JButton buttonLogin;
    private JLabel jLabelUsername, jLabelPassword;
    private JPasswordField passwordField;
    private JTextField textFieldUsername;
}
