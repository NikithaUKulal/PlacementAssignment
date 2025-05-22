package com.user.dao;

import com.user.model.User;
import com.user.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            
            pstmt.executeUpdate();
            System.out.println("User created successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
                users.add(user);
            }
            
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        
        return users;
    }
    
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
        }
        
        return null;
    }
    
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setInt(4, user.getId());
            
            pstmt.executeUpdate();
            System.out.println("User updated successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }
    
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User deleted successfully!");
            
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
} 