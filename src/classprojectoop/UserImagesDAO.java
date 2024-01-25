/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classprojectoop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author arets
 */
class UserImagesDAO {
    DatabaseTableCreater dbc ;
    private final Connection connection;


public UserImagesDAO(Connection connection) {
            this.connection = connection;
    }
    // Method to insert an image into the userimages table
   public void insertImage(byte[] imageData, String username) {
    // Define the SQL query
    String sql = "INSERT INTO UserImages (username, image) VALUES (?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        // Set the username and image data as parameters
        preparedStatement.setString(1, username);
        preparedStatement.setBytes(2, imageData);

        // Execute the SQL query
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately (log it, show a message, etc.)
    }
}

   
   
    // Provide a proper implementation for prepareStatement
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    void insertImage(byte[] imageData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
