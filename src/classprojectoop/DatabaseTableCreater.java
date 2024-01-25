/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classprojectoop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * DatabaseTableCreater class for managing database operations.
 * @author Samantha/Andrei Retsja
 */
public class DatabaseTableCreater {
     //  Database URL, username, and password
    
    static final String URL = "jdbc:mysql://localhost:3306/hermes";
    static String userM = "root";
    static String pass = "";
    private String insertUser;
    private String username;
    private String imagePath;
    
    
   /**
     * Method to get all user names from the database.
     *
     * @return List of user names
     */
    
    public List<String> getAllUsers() {
    List<String> users = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement()) {

        String getUsersQuery = "SELECT userName FROM users";  // Ensure "userName" is selected
        ResultSet resultSet = statement.executeQuery(getUsersQuery);

        while (resultSet.next()) {
            String userName = resultSet.getString("userName");
            users.add(userName);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Add additional logging or handle the exception as needed
    }
    return users;
}
    
   
  
   /**
     * Method to create the database and tables.
     */
    public void createDatabase(){
        try{
            
            Connection connection = DriverManager.getConnection(URL, userM, pass);
            Statement statement = connection.createStatement();

            // Create database if not exists
            String createDatabaseStatement = "CREATE DATABASE IF NOT EXISTS hermes";
            statement.executeUpdate(createDatabaseStatement);
            System.out.println("Database Created");

            // Switch to the created database
            connection = DriverManager.getConnection(URL, userM, pass);
            System.out.println("Connected to the new database");

       
           
            // Create users table
            String createTableUsers = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "userName VARCHAR(255) UNIQUE,"
                    + "password VARCHAR(255),"
                    + "email VARCHAR(255)UNIQUE,"
                    + "profileId INT "
                    // here can Added more Field if requred 
                    + ")";
             statement.executeUpdate(createTableUsers);
            System.out.println("Users Table Created");
            
           
           // Create messages table
            String createTableMessages = "CREATE TABLE IF NOT EXISTS messages ("
                   + " id INT AUTO_INCREMENT PRIMARY KEY,"
                   + " time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                   + " message VARCHAR(500),"
                   + " sender_id INT ,"
                   + " recipient_id INT ,"
                   + " FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,"
                   + " FOREIGN KEY (recipient_id) REFERENCES users(id) ON DELETE CASCADE " 
                   + ")";
            statement.executeUpdate(createTableMessages);
            System.out.println("Messages Table Created");
 
            
            // Create NewsFeeds table
            String createTableNews = "CREATE TABLE IF NOT EXISTS NewsFeed ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    +"time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "userName VARCHAR(255),"
                    + "newsFeed VARCHAR(255)"
                    //here can Added more Field if requred 
                    + ")";
             statement.executeUpdate(createTableNews);
            System.out.println("NewsFeeds table Created");
            
            
             // Create UserGroups table
             String createTableUserGroups = "CREATE TABLE IF NOT EXISTS UserGroups ("
                    + "groupID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "groupName VARCHAR(255) UNIQUE,"
                    + "description TEXT,"
                    + "createdBy VARCHAR(50) NOT NULL,"
                    + "createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            statement.executeUpdate(createTableUserGroups);
            System.out.println("UserGroups Table Created");
            
            
             // Create UserGroupMembers table
            String createUserGroupMembersTable = "CREATE TABLE IF NOT EXISTS UserGroupMembers ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "userName VARCHAR(255),"
                    + "groupName VARCHAR(255),"
                    + "FOREIGN KEY (userName) REFERENCES users(userName) ON DELETE CASCADE,"
                    + "FOREIGN KEY (groupName) REFERENCES UserGroups(groupName) ON DELETE CASCADE"
                    + ")";
                    statement.executeUpdate(createUserGroupMembersTable);
                    System.out.println("UserGroupMembers Table Created");
            
            
                    
            // Create UserImages table
            String createUserImagesTable = "CREATE TABLE IF NOT EXISTS UserImages ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(255) UNIQUE,"
                    + "image BLOB,"
                    + "FOREIGN KEY (username) REFERENCES users(userName) ON DELETE CASCADE"
                    + ")";
                    statement.executeUpdate(createUserImagesTable);
                    System.out.println("UserImages Table Created");
       
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

 
  
  // Updated method with image uploading
  private void uploadImage(String username, File selectedFile, boolean saveToDatabase) throws SQLException {
    if (selectedFile == null) {
        // Handle the case where selectedFile is null
        System.err.println("Selected file is null. Cannot upload image.");
        return;
    }

    if (saveToDatabase) {
        // Save the image to the database
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO UserImages(username, image) VALUES (?, ?)")) {

            preparedStatement.setString(1, username);

            // Read the image file into an InputStream
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                // Set the binary stream of the image into the prepared statement
                preparedStatement.setBinaryStream(2, fis, (int) selectedFile.length());

                // Execute the update
                preparedStatement.executeUpdate();
                System.out.println("Image uploaded successfully to the database.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    } else {
        // Display the image in the imageLabel
        try {
            byte[] imageData = Files.readAllBytes(selectedFile.toPath());
            ImageIcon imageIcon = new ImageIcon(imageData);
            imageLabel.setIcon(imageIcon);
            imageLabel.setText(""); // Clear any existing text
            System.out.println("Image displayed.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
    
    
    
    /**
     * Method to delete messages for a user.
     *
     * @param userId User ID
     */
public void deleteMessagesForUser(int userId) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         PreparedStatement statement = connection.prepareStatement(
                 "DELETE FROM messages WHERE sender_id = ? OR recipient_id = ?")) {

        statement.setInt(1, userId);
        statement.setInt(2, userId);
        statement.executeUpdate();

        System.out.println("Messages deleted for user with ID: " + userId);
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions appropriately
    }
}
    
   
 // Get the user ID based on the username from the database
    
   public int getRecipientIdByUsername(String userName) {
    int recipientId = -1; // Default value if the recipient is not found or an error occurs

    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT id FROM users WHERE userName = ?")) {

        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Retrieve the user ID from the result set
            recipientId = resultSet.getInt("id");
        } else {
            // Handle the case where the user with the given username is not found
            System.out.println("Recipient with username " + userName + " not found.");
        }
    } catch (java.sql.SQLException e) {
    e.printStackTrace();
    // Handle the SQL exception as needed
}
    return recipientId;
}

 public int getUserIdByUsername(String username) {
        int userId = -1; // Default value if user is not found

        try (Connection connection = getConnection()) {
            // Assuming you have a table named 'users' with columns 'id' and 'username'
            String sql = "SELECT id FROM users WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        return userId;
    }
        Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, userM, pass);
    }


     // Method to insert a user into the database

    public void insertUser(String userName, String password, String email, Integer profileId) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         PreparedStatement statement = connection.prepareStatement(
                 "INSERT INTO users(userName, password, email, profileId) VALUES (?, ?, ?, ?)")) {

        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setString(3, email);

        // Check if profileId is null before setting it
        if (profileId != null) {
            statement.setInt(4, profileId.intValue());  // Assuming profileId is an integer
        } else {
            statement.setNull(4, Types.INTEGER);
        }

        statement.executeUpdate();

        System.out.println("User Added");
    } catch (SQLIntegrityConstraintViolationException e) {
        // Handle duplicate entry (username or email already exists)
        System.out.println("Username or email already exists.");
        JOptionPane.showMessageDialog(null, "Username or email already exists.");
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle other exceptions appropriately in your application
    }
}
     
    /**
     *
     * @param message
     */
   
     // Method to send a message between users
   
   public void sendMessage(String message, Integer sender_id, Integer recipient_id) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         PreparedStatement statement = connection.prepareStatement(
                 "INSERT INTO messages(message, sender_id, recipient_id) VALUES (?, ?, ?)")) {

        statement.setString(1, message);
        statement.setInt(2, sender_id);  // Assuming sender_id is an integer
        statement.setInt(3, recipient_id);  // Assuming recipient_id is an integer

        statement.executeUpdate();

        System.out.println("Message Sent");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
     // Method to get messages for a specific user
   
     public List<String> getMessagesForUser(String userName) {
        List<String> messages = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, userM, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT message FROM messages WHERE recipient_id = (SELECT id FROM users WHERE userName = ?)")) {

            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String message = resultSet.getString("message");
                    messages.add(message);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
    
    /**
     *
     * @param NewsFeed
     */
   
  // Method to create a news feed entry
   
   public void createNewsFeed(String newsFeed, String userName) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement()) {


        // Insert the news feed   
        String insertNewsFeed = "INSERT INTO NewsFeed(userName, newsFeed) VALUES('" + newsFeed + "','" + userName + "')";
        statement.executeUpdate(insertNewsFeed);
        System.out.println("NewsFeed Posted");
   
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(DatabaseTableCreater.class.getName()).log(Level.SEVERE, null, ex);
    }
}
      
    /**
     * Method to get news feeds from the database.
     *
     * @return List of news feeds
     */
   public List<String> getNewsFeeds() throws SQLException {
        List<String> newsFeeds = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, userM, pass);
             Statement statement = connection.createStatement()) {

            String selectNewsFeeds = "SELECT newsFeed FROM NewsFeed";
            ResultSet resultSet = statement.executeQuery(selectNewsFeeds);

            while (resultSet.next()) {
                String newsFeed = resultSet.getString("newsFeed");
                newsFeeds.add(newsFeed);
            }
        }
        return newsFeeds;
    }
  
   
     // connected to Table named 'NewsFeed' with columns 'userName' and 'newsFeed'
    public List<NewsFeedEntry> getNewsFeedEntries() {
        List<NewsFeedEntry> entries = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT userName, newsFeed FROM NewsFeed")) {

            while (resultSet.next()) {
                String username = resultSet.getString("userName");
                String newsFeed = resultSet.getString("newsFeed");

                NewsFeedEntry entry = new NewsFeedEntry(username, newsFeed);
                entries.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        return entries;
    }
   
   
   public void createGroup(String groupName, String description, String createdBy) {
    // Check if any of the required fields is empty
    if (groupName.isEmpty() || createdBy.isEmpty()) {
        System.out.println("Group name and createdBy cannot be empty.");
        JOptionPane.showMessageDialog(null, "Group name and createdBy cannot be empty.");
        return;
    }
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         PreparedStatement statement = connection.prepareStatement(
                 "SELECT COUNT(*) FROM UserGroups WHERE groupName = ?");
         PreparedStatement insertStatement = connection.prepareStatement(
                 "INSERT INTO UserGroups (groupName, description, createdBy) VALUES (?, ?, ?)")) {

        // Check if the group already exists
        statement.setString(1, groupName);
        ResultSet groupResult = statement.executeQuery();
        groupResult.next();

        int groupCount = groupResult.getInt(1);

        if (groupCount > 0) {
            System.out.println("Group already exists.");
            JOptionPane.showMessageDialog(null, "Group already exists.");
            return;
        }

        // Create the group using a prepared statement
        insertStatement.setString(1, groupName);
        insertStatement.setString(2, description);
        insertStatement.setString(3, createdBy);

        insertStatement.executeUpdate();

        System.out.println("Group created successfully.");
        JOptionPane.showMessageDialog(null, "Group created successfully.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(DatabaseTableCreater.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 
   public boolean isUserExists(String userName) {
    // Execute a query to check if the user exists in the "users" table
    String checkUserExistence = "SELECT COUNT(*) FROM users WHERE userName = '" + userName + "'";

    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement();
         ResultSet userResult = statement.executeQuery(checkUserExistence)) {

        userResult.next();
        int userCount = userResult.getInt(1);

        return userCount > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception appropriately
        return false;
    }
}
   
   
   
   public boolean userExists(String userName) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement()) {
        String query = "SELECT COUNT(*) FROM users WHERE userName = '" + userName + "'";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int userCount = resultSet.getInt(1);
        return userCount > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}
   
  public boolean groupExists(String groupName) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement()) {
        String query = "SELECT COUNT(*) FROM UserGroups WHERE groupName = '" + groupName + "'";
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int groupCount = resultSet.getInt(1);
        return groupCount > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
} 
   
   // In your DatabaseTableCreater class
public List<String> getExistingGroups() {
    List<String> existingGroups = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement()) {

        String query = "SELECT groupName FROM UserGroups";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String groupName = resultSet.getString("groupName");
            existingGroups.add(groupName);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle the exception according to your application's requirements
    }

    return existingGroups;
}
   

public void addUserToGroup(String userName, String groupName) {
    try (Connection connection = DriverManager.getConnection(URL, userM, pass);
         Statement statement = connection.createStatement()) {
        String query = "INSERT INTO UserGroupMembers (userName, groupName) VALUES ('" + userName + "', '" + groupName + "')";
        statement.executeUpdate(query);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    private static class imageLabel {

        private static void setIcon(ImageIcon imageIcon) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void setText(String string) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public imageLabel() {
        }
    }


}
    


    


   

    
 


    
