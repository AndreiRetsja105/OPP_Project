/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classprojectoop;


import java.sql.SQLException;


/**
 *
 * @author Jason / Andrei Retsja
 */
public class MainApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // Create an instance of DatabaseTableCreater for database operations
         DatabaseTableCreater dbc = new DatabaseTableCreater();
         // Create database tables
         dbc.createDatabase();
         // Create instances of various forms used in the application
         FrameForPanels ffp = new FrameForPanels();
         GrupPageForm fpf = new GrupPageForm();
         MainPageImage mpi = new MainPageImage();

         
    }
        
    }
    
    
    
    
 
