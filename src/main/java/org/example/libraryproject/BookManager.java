package org.example.libraryproject;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private BookDAO dao = new BookDAO();

    // search for a book by its title
    public Book searchBooks(String title) {
        return dao.getBookByTitle(title);
    }

    // add a new book
    public void addBook(Book book) throws ClassNotFoundException {
        dao.insertBook(book);
    }

    // get list of all books in the database
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    public static void main(String[] args) {
        // get user input from command line to add a new book
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

        // create a new book object based on user input
        Book book;
        if (isEbook) {
            book = new eBook(title, pages, description, isEbook, bookFileName);
        } else {
            book = new PhysicalBook(title, pages, description, isEbook, bookFileName);
        }
        try {
            // add book to database and create a file for the book
            manager.addBook(book);
            createFile(bookFileName, scanner);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // create a file for the book
    // this method is used to create a file for the book when the user adds a new book
    // the file is stored in the bookFiles folder
    // the file name is the same as the book file name
    // the file is created with the first line of the book file as its content
    // the file is set to be readable and writable by the user
    // the file is closed after it is created
    // if the file already exists, it will not be created again
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

