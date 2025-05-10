module org.example.libraryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.libraryproject to javafx.fxml;
    exports org.example.libraryproject;
}