package org.example.libraryproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SearchUI extends Application {

    private Label messageLabel;
    private TextField titleTextField;
    private Button searchButton;
    private Button checkOutButton;
    private BookManager bookManager;
    private Button readButton;


    public void searchScene(Stage stage) {
        BorderPane root = new BorderPane();


        //Main Layout
        VBox layout = new VBox(20);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        BookManager manager = new BookManager();

        //title label
        Label titleLabel = new Label("\uD83D\uDCDASearch Books");
        titleLabel.setStyle("-fx-font-size: 75px; -fx-font-weight: bold; -fx-text-fill: #333;");

        //message label and text field for search
        messageLabel = new Label("Enter a book title:");
        messageLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;-fx-text-fill: #333;");
        titleTextField = new TextField();
        titleTextField.setPromptText("Search Book");
        titleTextField.setPrefSize(800, 25);
        titleTextField.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        searchButton = new Button("Search");
        searchButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-background-color: #ADD8E6;");
        searchButton.setPrefSize(200,35);
        searchButton.setOnAction(e -> BookInfo(stage));

        HBox searchRow = new HBox(10, messageLabel, titleTextField, searchButton);
        searchRow.setAlignment(javafx.geometry.Pos.CENTER);

        // book manager contains stored books
        bookManager = manager;

        //list all books
        ListView<String> bookList = new ListView<>();
        bookList.setPrefSize(100,250);
        bookList.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        //show all book title
        List<Book> allBooks = manager.getAllBooks();
        for(Book book : allBooks) {
            String booktitle = book.getBookTitle();
            bookList.getItems().add(booktitle);
        }

        //Top bar for sign out
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.BASELINE_RIGHT);
        topBar.setPrefSize(150,25);
        topBar.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        //button for sign out
        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction(e -> {
            loginScene(stage);
        });
        topBar.getChildren().add(signOutButton);


        //grouping all layout
        layout.getChildren().addAll(titleLabel,searchRow, bookList);
        root.setCenter(layout);
        root.setTop(topBar);


        // window title
        stage.setTitle("Search Books");
        Scene scene = new Scene(root, 500, 350);
        stage.setScene(scene);

        // show stage and full screen
        stage.setFullScreen(true);
        stage.show();

    }

    public void BookInfo(Stage stage) {
        //store the state of fullscreen
        boolean wasFullScreen = stage.isFullScreen();

        VBox layout = new VBox(10);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> searchScene(stage));
        layout.getChildren().add(backButton);

        String title = titleTextField.getText();
        Book book = bookManager.searchBooks(title);
        if (book == null) {
            Label notFound = new Label("Book not found.");
            layout.getChildren().add(notFound);
            Scene scene = new Scene(layout, 500, 350);
            stage.setScene(scene);
            stage.setFullScreen(true
            );
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

            stage.setTitle(title);
            Scene scene = new Scene(layout, 500, 350);
            stage.setFullScreen(true);
            stage.setScene(scene);
        }
        //fullscreen and show stage
        stage.setFullScreen(true);
        stage.show();
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

        //creating a back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            ((Stage) backButton.getScene().getWindow()).close();
        });

        HBox topBar = new HBox(backButton);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.TOP_LEFT);

        //borderPane layout
        BorderPane layout = new BorderPane();
        layout.setTop(topBar);
        layout.setCenter(textArea);


        Scene scene = new Scene(layout, 500, 350);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setFullScreen(true);
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
        stage.setFullScreen(true);
        stage.show();
    }

    public void loginScene(Stage stage) {
        VBox layout = new VBox(15);
        layout.setPadding(new javafx.geometry.Insets(50));
        layout.setStyle("-fx-background-color: #FAF9F6;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Label loginTitle = new Label("\uD83D\uDCDA ReadNest \uD83C\uDFE1");
        loginTitle.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-text-fill: #333;");

        //username
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill: #333;");
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter Username");
        usernameTextField.setPrefSize(700, 50);
        usernameTextField.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        HBox usernameRow = new HBox(10,usernameLabel,usernameTextField);
        usernameRow.setAlignment(javafx.geometry.Pos.CENTER);

        //password
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill: #333");
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Enter Password");
        passwordTextField.setPrefSize(700, 50);
        passwordTextField.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        HBox passwordRow = new HBox(10,passwordLabel,passwordTextField);
        passwordRow.setAlignment(javafx.geometry.Pos.CENTER);


        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-font-size: 25px;-fx-background-color: #FF0033; -fx-font-weight: bold;");

        //login button
        Button loginButton = new Button("Login");
        loginButton.setPrefSize(200,50);
        loginButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-background-color: #ADD8E6");
        loginButton.setOnAction(e ->{
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            if (username.equals("admin") && password.equals("password")) {
                searchScene(stage);
            } else {
                errorLabel.setText("Invalid Username or Password.");
            }
        });

        //register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> registerScene(stage));
        registerButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        registerButton.setPrefSize(200,50);

        HBox buttonRow = new HBox(10,registerButton,loginButton);
        buttonRow.setAlignment(javafx.geometry.Pos.CENTER);


        layout.getChildren().addAll(loginTitle,usernameRow, passwordRow, buttonRow, errorLabel);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Library Login");

        stage.setFullScreen(true);
        stage.show();
    }

    public void registerScene(Stage stage) {
        VBox layout = new VBox(25);
        layout.setPadding(new Insets(35));
        layout.setStyle("-fx-background-color: #FAF9F6;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        //title
        Label registerTitle = new Label("\uD83D\uDCDA Register New ReadNest Account");
        registerTitle.setStyle("-fx-font-size: 75px; -fx-font-weight: bold; -fx-text-fill: #333;");

        //username section

        //username label
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-weight: bold;-fx-text-fill: #333;");
        //username Text field
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter Username");
        usernameTextField.setPrefSize(700, 50);
        usernameTextField.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        //grouping label and text field
        HBox userNameRow = new HBox(10, usernameLabel, usernameTextField);
        userNameRow.setPrefSize(700, 50);
        userNameRow.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        userNameRow.setAlignment(javafx.geometry.Pos.CENTER);


        //password section

        //user name label
        Label passwordLabel = new Label("Enter Password: ");
        passwordLabel.setStyle("-fx-font-weight: bold;-fx-text-fill: #333;");
        //password text field
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Enter Password");
        passwordTextField.setPrefSize(700, 50);
        passwordTextField.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        //grouping label and text field
        HBox passwordRow = new HBox(10, passwordLabel, passwordTextField);
        passwordRow.setPrefSize(700, 50);
        passwordRow.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        passwordRow.setAlignment(javafx.geometry.Pos.CENTER);

        //confirm password section

        //confirmation label
        Label confirmPass = new Label("Confirm Password: ");
        confirmPass.setStyle("-fx-font-weight: bold;-fx-text-fill: #333;");
        //confirmation text field
        PasswordField confirmPassTextField = new PasswordField();
        confirmPassTextField.setPromptText("Re-enter Password to confirm");
        confirmPassTextField.setPrefSize(700, 50);
        confirmPassTextField.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        //grouping label and text field
        HBox confirmPassRow = new HBox(10, confirmPass, confirmPassTextField);
        confirmPassRow.setPrefSize(700, 50);
        confirmPassRow.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
        confirmPassRow.setAlignment(javafx.geometry.Pos.CENTER);

        //register button
        Label messageLabel = new Label();
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-background-color: #ADD8E6");
        registerButton.setPrefSize(200,50);
        registerButton.setOnAction(e -> {
            messageLabel.setText("Registration currently unavailable.");
            messageLabel.setStyle("-fx-font-size: 25px;-fx-background-color: #FF0033; -fx-font-weight: bold;");
        });

        //back to login page button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        backButton.setPrefSize(200,50);
        backButton.setOnAction(e -> loginScene(stage));

        HBox buttonRow = new HBox(10,backButton,registerButton);
        buttonRow.setAlignment(javafx.geometry.Pos.CENTER);

        layout.getChildren().addAll(registerTitle, userNameRow, passwordRow, confirmPassRow, buttonRow, messageLabel);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Register New Account");

        stage.setFullScreen(true);
        stage.show();

    }


    @Override
    public void start(Stage stage) throws Exception {
        loginScene(stage);
        searchScene(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
