
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.User;


public class UserDAO {
    
     public boolean checkLogin(String login, String password){
        Connection connect = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = connect.prepareStatement("SELECT * FROM users WHERE login = ? and password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                check = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(connect, stmt, rs);
        }
        
        return check;
        
    }
    
}
