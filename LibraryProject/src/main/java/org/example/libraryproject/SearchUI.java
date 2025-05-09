package org.example.libraryproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
            Scene scene = new Scene(layout, 1000, 1500);
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

    public void loginScene(Stage stage) {
        VBox layout = new VBox(15);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setStyle("-fx-background-color: white;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Label loginTitle = new Label("ReadNest");
        loginTitle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #333;");

        //username
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-weight: bold;-fx-text-fill: #333;");
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter Username");


        //password
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-font-weight: bold;-fx-text-fill: #333;");
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Enter Password");


        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-background-color: #FF0033; -fx-font-weight: bold;");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e ->{
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            if (username.equals("admin") && password.equals("password")) {
                createLayout(stage);
            } else {
                errorLabel.setText("Invalid Username or Password.");
            }
        });

        layout.getChildren().addAll(loginTitle,usernameLabel, usernameTextField, passwordLabel, passwordTextField, loginButton, errorLabel);
        Scene scene = new Scene(layout, 500, 350);
        stage.setScene(scene);
        stage.setTitle("Library Login");
        stage.show();


    }

    @Override
    public void start(Stage stage) throws Exception {
        //stage.setMaximized(true);  //fullscreen but it look weird rn
        loginScene(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
