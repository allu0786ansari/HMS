package officemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/office_management"; // Update with your database name
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = "Update this section with your db password"; // Update with your MySQL password

    // Establish connection to the database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Validate user credentials
    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add a new method to save patient details
    public static boolean savePatientData(String patientName, String fatherName, String cnic, String illness, String doctorAssigned) {
        String query = "INSERT INTO patient_details (patient_name, father_name, cnic, illness, doctor_assigned) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, patientName);
            pstmt.setString(2, fatherName);
            pstmt.setString(3, cnic);
            pstmt.setString(4, illness);
            pstmt.setString(5, doctorAssigned);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Method to save doctor availability
    public static boolean saveDoctorAvailability(String doctorName, String appointmentTime, double appointmentFee, String paymentOption) {
        String query = "INSERT INTO doctoravailability (doctor_name, appointment_time, appointment_fee, payment_option) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, doctorName);
            pstmt.setString(2, appointmentTime);
            pstmt.setDouble(3, appointmentFee);
            pstmt.setString(4, paymentOption);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
