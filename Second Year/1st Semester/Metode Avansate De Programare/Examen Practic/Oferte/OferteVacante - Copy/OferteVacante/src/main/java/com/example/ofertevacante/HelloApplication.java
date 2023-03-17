package com.example.ofertevacante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    static List<Long> clientIds;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        /// clientIds.forEach(c=> openWindow(client...));
        clientIds.forEach(
                c ->{
                    FXMLLoader fxmlLoader4= new FXMLLoader(HelloApplication.class.getResource("client-view.fxml"));
                    Scene scene4 = null;
                    try {
                        scene4 = new Scene(fxmlLoader4.load(), 550, 400);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage4 = new Stage();
                    ClientController clientController = fxmlLoader4.getController();
                    clientController.setClient(c);
                    stage4.setTitle("Clientul" + c);
                    stage4.setScene(scene4);
                    stage4.show();
        }
        );


    }

    public static void main(String[] args) {

        clientIds = Arrays.stream(args).map(x->Long.valueOf(x)).collect(Collectors.toList());
        launch();
    }
}