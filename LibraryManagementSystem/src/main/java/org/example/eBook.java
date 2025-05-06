package org.example;

import java.util.HashMap;

public class eBook extends Book{
    public eBook(String bookTitle) {
        super(bookTitle);
    }

    private HashMap<String, Book> ebooks;
}
