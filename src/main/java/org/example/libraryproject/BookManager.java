package org.example.libraryproject;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

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

    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    public static void main(String[] args) {
        BookManager manager = new BookManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter number of pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Is this a ebook? (true/false): ");
        boolean isEbook = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter book file name: ");
        String bookFileName = scanner.nextLine();

        Book book;
        if (isEbook) {
            book = new eBook(title, pages, description, isEbook, bookFileName);
        } else {
            book = new PhysicalBook(title, pages, description, isEbook, bookFileName);
        }
        try {
            manager.addBook(book);
            createFile(bookFileName, scanner);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String bookFileName, Scanner scanner) {
        File file = new File("bookFiles/"+bookFileName);
        try {
            file.createNewFile();
            file.setReadable(true);
            file.setWritable(true);
            System.out.print("File contents: ");
            FileWriter writer = new FileWriter(file);
            writer.write(scanner.nextLine());
            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to create file.");
        }
    }

}

