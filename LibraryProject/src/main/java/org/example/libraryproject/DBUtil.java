package org.example.libraryproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/librarymanagementsystem"; // database name
        String user = "root"; // username
        String password = "c@tF33t$"; // password
        return DriverManager.getConnection(url, user, password);
    }
}
