package com.example.examen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    @FXML
    void deschideFereastra(MouseEvent event) throws IOException {
//        Stage stage = new Stage();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("window-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(),400,400);
//        stage.setTitle("Hello");
//        stage.setScene(scene);
//        stage.show();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
    }

}