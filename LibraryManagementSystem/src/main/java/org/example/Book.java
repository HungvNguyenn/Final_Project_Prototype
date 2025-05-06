package org.example;

public class Book {
    private String bookTitle;
    private int numberOfPages;
    private String description;
    private boolean isEbook;

    public Book() {}

    public Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Book(String bookTitle, int numberOfPages, String description, boolean isEbook) {
        this.bookTitle = bookTitle;
        this.numberOfPages = numberOfPages;
        this.description = description;
        this.isEbook = isEbook;
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

    public String isEbook() {
        if(isEbook) {
            return "Ebook";
        } else {
            return "Physical Book";
        }
    }
}

