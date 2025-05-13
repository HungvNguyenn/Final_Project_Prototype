package org.example.libraryproject;

public class PhysicalBook extends Book{
    public PhysicalBook(String bookTitle) {
        super(bookTitle);
    }

    public PhysicalBook(String title, int pages, String description, boolean isEbook,String bookFileName) {
        super(title, pages, description, isEbook, bookFileName);
    }
}
