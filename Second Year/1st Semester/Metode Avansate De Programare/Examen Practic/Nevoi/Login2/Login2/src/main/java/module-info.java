module com.example.login2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.login2 to javafx.fxml;
    exports com.example.login2;

    opens com.example.login2.domain to javafx.fxml;
    exports com.example.login2.domain;


}