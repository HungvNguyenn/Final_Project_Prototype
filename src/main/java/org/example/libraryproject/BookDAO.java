package org.example.libraryproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Insert a book into the database
    public void insertBook(Book book) throws ClassNotFoundException {
        // SQL statement for inserting a book into the database
        String sql = "INSERT INTO book (title, pages, description, is_ebook, filename) VALUES (?, ?, ?, ?, ?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DBUtil.getConnection()) {
            // create table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS book (" +
                    "title VARCHAR(255) PRIMARY KEY," +
                    "pages INT," +
                    "description TEXT," +
                    "is_ebook BOOLEAN," +
                    "filename VARCHAR(255))";
            // ensure auto-commit
            conn.createStatement().execute(createTableSQL);

            PreparedStatement stmt = conn.prepareStatement(sql);
            // insert book into database
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

    // get a book by title from the database
    public Book getBookByTitle(String title) {
        // SQL statement for getting a book by title from the database
        String sql = "SELECT * FROM book WHERE title = ?";

        // create a new book object based on the book title
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            // check if book exists in the database
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

        return null; // book not found
    }

    // get list of all books in the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        // SQL statement for getting all books from the database
        String sql = "SELECT * FROM book";
        // create a new book object based on the book title
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through all books in the database and add them to the list
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getInt("pages"),
                        rs.getString("description"),
                        rs.getBoolean("is_ebook"),
                        rs.getString("filename")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


}

