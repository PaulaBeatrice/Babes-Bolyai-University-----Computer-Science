module com.example.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen to javafx.fxml;
    exports com.example.examen;
    opens com.example.examen.controller to javafx.fxml;
}