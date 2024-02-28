module com.example.ressource {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ressource to javafx.fxml;
    exports com.example.ressource;
}