package org.example;

import java.util.HashMap;

public class BookManager {
    // map of books with titles and pages
    private HashMap<String, Book> books;

    public BookManager() {
        books = new HashMap<>();

        // Sample books
        books.put("Harry Potter", new Book("Harry Potter", 500,"He's a wizard",true));
        books.put("The Hobbit", new Book("The Hobbit", 300, "He's little and hairy",false));
        books.put("1984", new Book("1984", 328, "Dystopian society",false));
        books.put("Percy Jackson", new Book("Percy Jackson", 350, "He's a demigod",true));
    }

    // search for a book by it's title
    public Book searchBooks(String title) {
        if (title == null || title.isBlank()) return null;

        return books.get(title);
    }
}

