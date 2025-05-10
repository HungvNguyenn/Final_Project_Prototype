package org.example.libraryproject;

import java.sql.*;

public class BookDAO {

    // Insert a book into the database
    public void insertBook(Book book) throws ClassNotFoundException {
        String sql = "INSERT INTO book (title, pages, description, is_ebook, filename) VALUES (?, ?, ?, ?, ?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DBUtil.getConnection()) {
            // Create table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS book (" +
                    "title VARCHAR(255) PRIMARY KEY," +
                    "pages INT," +
                    "description TEXT," +
                    "is_ebook BOOLEAN," +
                    "filename VARCHAR(255))";
            conn.createStatement().execute(createTableSQL);

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, book.getBookTitle());
            stmt.setInt(2, book.getNumberOfPages());
            stmt.setString(3, book.getDescription());
            stmt.setBoolean(4, book.isEbook());
            stmt.setString(5, book.getBookFile());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get a book by title from the database
    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM book WHERE title = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getString("title"),
                        rs.getInt("pages"),
                        rs.getString("description"),
                        rs.getBoolean("is_ebook"),
                        rs.getString("filename")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Book with title '" + title + "' not found in database.");

        return null; // book not found
    }
}

