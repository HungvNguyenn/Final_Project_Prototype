package org.example.libraryproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SearchUI extends Application {

    private Label messageLabel;
    private TextField titleTextField;
    private Button searchButton;
    private Button checkOutButton;
    private BookManager bookManager;
    private Button readButton;


    public void createLayout(Stage primaryStage) {
        VBox layout = new VBox(10);
        BookManager manager = new BookManager();
        messageLabel = new Label("Enter a book title:");
        titleTextField = new TextField();
        searchButton = new Button("Search");
        searchButton.setOnAction(e -> BookInfo(primaryStage));
        // book manager contains stored books
        bookManager = manager;
        // window title
        primaryStage.setTitle("Search Books");
        layout.getChildren().addAll(messageLabel, titleTextField, searchButton);
        Scene scene = new Scene(layout, 500, 350);
        primaryStage.setScene(scene);
        // show stage
        primaryStage.show();
    }

    public void BookInfo(Stage primaryStage) {
        VBox layout = new VBox(10);
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> createLayout(primaryStage));
        layout.getChildren().add(backButton);
        String title = titleTextField.getText();
        Book book = bookManager.searchBooks(title);
        if (book == null) {
            Label notFound = new Label("Book not found.");
            layout.getChildren().add(notFound);
            Scene scene = new Scene(layout, 500, 350);
            primaryStage.setScene(scene);
            return;
        } else {
            String bookType;
            if (book.isEbook()) {
                bookType = "Ebook";
            } else {
                bookType = "Physical Book";
            }
            Label info = new Label("Book found:\nTitle: " + book.getBookTitle() +
                    "\nPages: " + book.getNumberOfPages() + "\nDescription: " + book.getDescription() +
                    "\nBook Type: " + bookType);

            readButton = new Button("Read");
            checkOutButton = new Button("Check Out");
            readButton.setOnAction(e -> openBook(title));
            checkOutButton.setOnAction(e -> checkOutBook(title));
            if (book.isEbook()) {
                layout.getChildren().addAll(info, readButton);
            } else {
                layout.getChildren().addAll(info, checkOutButton);
            }

            primaryStage.setTitle(title);
            Scene scene = new Scene(layout, 500, 350);
            primaryStage.setScene(scene);
        }
        primaryStage.show();
    }

    // open readable file
    public void openBook(String title) {
        Book book = bookManager.searchBooks(title);
        File file = new File("bookFiles/"+book.getBookFile());
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        try {
            String content = Files.readString(file.toPath());
            textArea.setText(content);
        } catch (IOException e) {
            textArea.setText("Failed to read book file.");
        }

        Scene scene = new Scene(textArea, 500, 350);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

    public void checkOutBook(String title) {
        VBox layout = new VBox(10);
        Label checkedOut = new Label("Book checked out.");
        Button readButton = new Button("Read");
        readButton.setOnAction(e -> openBook(title));
        layout.getChildren().addAll(checkedOut, readButton);
        Scene scene = new Scene(layout, 500, 350);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        createLayout(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
