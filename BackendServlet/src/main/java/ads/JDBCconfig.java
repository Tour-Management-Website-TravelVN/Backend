package ads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
// This class can be used to initialize the database connection
public class JDBCconfig {
    protected static Connection initializeDatabase()
        throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
//        String dbName = "tour_booking_system";
//        String dbUsername = "Unknown";
//        String dbPassword = "Drsunknoen";
        
        String dbName = "tour_booking_system";
        String dbUsername = "root";
        String dbPassword = "abc123!";
  
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                                                    dbUsername, 
                                                     dbPassword);
        return con;
    }
}