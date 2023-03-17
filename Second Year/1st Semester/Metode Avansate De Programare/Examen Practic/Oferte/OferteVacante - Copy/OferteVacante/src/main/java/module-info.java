module com.example.ofertevacante {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ofertevacante to javafx.fxml;
    exports com.example.ofertevacante;
    opens com.example.ofertevacante.domain to javafx.fxml;
    exports com.example.ofertevacante.domain;
}