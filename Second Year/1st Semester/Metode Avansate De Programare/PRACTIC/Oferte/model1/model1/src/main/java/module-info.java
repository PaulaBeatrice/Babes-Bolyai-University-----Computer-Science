module com.example.model1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.model1 to javafx.fxml;
    opens com.example.model1.domains to javafx.base;
    exports com.example.model1;
}