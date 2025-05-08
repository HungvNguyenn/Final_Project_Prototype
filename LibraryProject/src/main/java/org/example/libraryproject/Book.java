package org.example.libraryproject;

public class Book {
    private String bookTitle;
    private int numberOfPages;
    private String description;
    private boolean isEbook;
    private String bookFilename;

    public Book() {}

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Book(String bookTitle, int numberOfPages, String description, boolean isEbook, String bookFilename) {
        this.bookTitle = bookTitle;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.isEbook = isEbook;
        this.bookFilename = bookFilename;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEbook() {
        return isEbook;
    }

    public String getBookFile() {
        return "bookFiles/"+bookFilename;
    }
}

