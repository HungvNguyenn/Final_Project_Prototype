package org.example.libraryproject;

import java.sql.*;

public class UserDAO {

    // Register a new user
    public boolean registerUser(User user) throws ClassNotFoundException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DBUtil.getConnection()) {
            // ensure auto-commit
            conn.setAutoCommit(true);
            // create table if it doesn't exist
            ensureTableExists(conn);
            // insert user
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                String username = user.getUsername();
                String password = user.getPassword();
                // check if username exists before registering
                if (validateUser(username, password)) {
                    System.out.println("Username already exists.");
                    return false;
                } else {
                    stmt.setString(1, username);
                    stmt.setString(2, password); // not encrypted in database
                    stmt.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                // check if it's a duplicate key violation
                if (e.getSQLState().startsWith("23")) { // integrity constraint violation
                    System.out.println("Username already exists.");
                } else {
                    e.printStackTrace();
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // catching error from getConnection or ensureTableExists
            System.out.println("Insert failed: " + e.getMessage());
            return false;
        }
    }

    // create table if it doesn't exist
    private void ensureTableExists(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "username VARCHAR(255) PRIMARY KEY," +
                "password VARCHAR(255))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }


    // login: check if user exists
    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

