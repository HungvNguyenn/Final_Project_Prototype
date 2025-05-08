package org.example.libraryproject;

import java.util.HashMap;

public class BookManager {
    private BookDAO dao = new BookDAO();

    // Search for a book by its title
    public Book searchBooks(String title) {
        return dao.getBookByTitle(title);
    }

    // Add a new book
    public void addBook(Book book) throws ClassNotFoundException {
        dao.insertBook(book);
    }

    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Book book = new Book("Magic School Bus", 30, "A magic bus that goes to school", false, "magicschoolbus.txt");
        try {
            manager.addBook(book);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

