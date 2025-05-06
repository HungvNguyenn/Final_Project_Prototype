package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.File;

public class SearchUI extends Application {

    private Label messageLabel;
    private TextField titleTextField;
    private Button searchButton;
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
        String title = titleTextField.getText();
        Book book = bookManager.searchBooks(title);
        Label info = new Label("Book found:\nTitle: " + book.getBookTitle() +
                "\nPages: " + book.getNumberOfPages() + "\nDescription: " + book.getDescription() +
                "\nBook Type: " + book.isEbook());
        readButton = new Button("Read");
        readButton.setOnAction(e-> openBook(title));
        layout.getChildren().addAll(info, readButton);
        primaryStage.setTitle(title);
        Scene scene = new Scene(layout,500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void openBook(String title) {
        Book book = bookManager.searchBooks(title);
        String filename = "Books/" + title + ".txt";
        File file = new File(filename);

        if(file.exists()) {
            try {
                String content = java.nio.file.Files.readString(file.toPath());

                // Display the content in a new window
                Stage readStage = new Stage();
                VBox layout = new VBox(10);
                javafx.scene.control.TextArea textArea = new javafx.scene.control.TextArea(content);
                textArea.setWrapText(true);
                textArea.setEditable(false);

                layout.getChildren().add(textArea);
                Scene scene = new Scene(layout, 600, 400);
                readStage.setTitle("Reading: " + title);
                readStage.setScene(scene);
                readStage.show();

            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            // Show a simple error message
            Stage errorStage = new Stage();
            VBox layout = new VBox(10);
            javafx.scene.control.Label label = new javafx.scene.control.Label("Book file not found: ");
            layout.getChildren().add(label);
            Scene scene = new Scene(layout, 400, 100);
            errorStage.setTitle("File Not Found");
            errorStage.setScene(scene);
            errorStage.show();
        }
    }



    @Override
    public void start(Stage stage) throws Exception {
        createLayout(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
