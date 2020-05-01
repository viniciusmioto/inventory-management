/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Products;

/**
 *
 * @author vinicius
 */
public class ProductDAO {
    private Connection connect = null;
    
    public ProductDAO(){
        connect = ConnectionFactory.getConnection();
    }
    
    public void create(Products prod){
        PreparedStatement stmt = null;
        String sql = "INSERT INTO products (description, quantity, price) VALUES (?, ?, ?)";
        try {
            stmt = connect.prepareStatement(sql);
            stmt.setString(1, prod.getDescription());
            stmt.setInt(2, prod.getQuantity());
            stmt.setDouble(3, prod.getPrice());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "SAVED, OK!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR WHILE SAVING...");
        }finally{
            ConnectionFactory.closeConnection(connect, stmt);
        }
        
    }
    
    public List<Products> read(){
        Connection connect = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Products> products = new ArrayList<>();
        
        try {
            stmt = connect.prepareStatement("SELECT * FROM products");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Products prod = new Products();
                prod.setId(rs.getInt("id"));
                prod.setDescription(rs.getString("description"));
                prod.setPrice(rs.getDouble("price"));
                prod.setQuantity(rs.getInt("quantity"));
                products.add(prod);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(connect, stmt, rs);
        }
        
        return products;
        
    }
    
    public void update(Products prod){
        
        PreparedStatement stmt = null;
        String sql = "UPDATE `products` SET `description` = ?, `quantity` = ?, `price` = ?  WHERE `products`.`id` = ?";
        try {
            stmt = connect.prepareStatement(sql);
            stmt.setString(1, prod.getDescription());
            stmt.setInt(2, prod.getQuantity());
            stmt.setDouble(3, prod.getPrice());
            stmt.setInt(4, prod.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "UPDATED, OK!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR WHILE UPDATING...");
            System.out.println("ID: " + prod.getId() + "\nDescript: " + prod.getDescription() +
                    "\nPrice: " + prod.getPrice() + "\nQuantity: " + prod.getQuantity());
        }finally{
            ConnectionFactory.closeConnection(connect, stmt);
        }
        
    }
    
    public void delete(Products prod){
        
        PreparedStatement stmt = null;
        String sql = "DELETE FROM `products` WHERE `products`.`id` = ?";
        try {
            stmt = connect.prepareStatement(sql);
            stmt.setInt(1, prod.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "DELETED, OK!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR WHILE DELETING...");
        }finally{
            ConnectionFactory.closeConnection(connect, stmt);
        }
        
    }
    
    public List<Products> search(String description){
        Connection connect = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Products> products = new ArrayList<>();
        
        try {
            stmt = connect.prepareStatement("SELECT * FROM products WHERE description LIKE ?");
            stmt.setString(1, "%"+description+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Products prod = new Products();
                prod.setId(rs.getInt("id"));
                prod.setDescription(rs.getString("description"));
                prod.setPrice(rs.getDouble("price"));
                prod.setQuantity(rs.getInt("quantity"));
                products.add(prod);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(connect, stmt, rs);
        }
        
        return products;
        
    }

}
