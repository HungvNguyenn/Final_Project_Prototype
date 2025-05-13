package org.example.libraryproject;

import java.util.HashMap;

public class eBook extends Book{
    public eBook(String bookTitle) {
        super(bookTitle);
    }

    public eBook(String title, int pages, String description, boolean isEbook,String bookFileName) {
        super(title, pages, description, isEbook, bookFileName);
    }
}
