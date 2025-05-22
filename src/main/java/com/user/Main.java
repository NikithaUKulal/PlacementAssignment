package com.user;

import com.user.dao.UserDAO;
import com.user.model.User;
import com.user.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize database and create table
        initializeDatabase();
        
        UserDAO userDAO = new UserDAO();
        
        // Create users
        System.out.println("\n=== Creating Users ===");
        User user1 = new User("John Doe", "john@example.com", "password123");
        User user2 = new User("Jane Smith", "jane@example.com", "password456");
        
        userDAO.createUser(user1);
        userDAO.createUser(user2);
        
        // Read all users
        System.out.println("\n=== Reading All Users ===");
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        
        // Update user
        System.out.println("\n=== Updating User ===");
        User userToUpdate = userDAO.getUserById(1);
        if (userToUpdate != null) {
            userToUpdate.setName("John Updated");
            userToUpdate.setEmail("john.updated@example.com");
            userDAO.updateUser(userToUpdate);
        }
        
        // Read updated user
        System.out.println("\n=== Reading Updated User ===");
        User updatedUser = userDAO.getUserById(1);
        System.out.println(updatedUser);
        
        // Delete user
        System.out.println("\n=== Deleting User ===");
        userDAO.deleteUser(2);
        
        // Read remaining users
        System.out.println("\n=== Reading Remaining Users ===");
        users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        
        // Close database connection
        DatabaseConnection.closeConnection();
    }
    
    private static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Create users table
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "password TEXT NOT NULL" +
                    ")";
            
            stmt.execute(sql);
            System.out.println("Database initialized successfully!");
            
        } catch (Exception e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
} 