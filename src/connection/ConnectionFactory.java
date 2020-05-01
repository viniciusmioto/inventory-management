package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mercadinho";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("CONNECTION ERROR!!! :c", ex);
        }
    }
    
    public static void closeConnection(Connection connect){   
        try {
            if(connect != null){
                connect.close();
            } 
        }catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void closeConnection(Connection connect, PreparedStatement stmt){   
        closeConnection(connect);
        
        try {
            if(stmt != null){
                stmt.close();
            } 
        }catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void closeConnection(Connection connect, PreparedStatement stmt, ResultSet rs){   
        closeConnection(connect, stmt);
        
        try {
            if(rs != null){
                rs.close();
            } 
        }catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
