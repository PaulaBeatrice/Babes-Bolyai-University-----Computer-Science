package com.example.spital;

import com.example.spital.controller.PacientiController;
import com.example.spital.controller.PatController;
import com.example.spital.domain.Pacient;
import com.example.spital.domain.Pat;
import com.example.spital.repository.Repository;
import com.example.spital.repository.factory.RepositoryEntity;
import com.example.spital.repository.factory.RepositoryFactory;
import com.example.spital.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Repository<Integer, Pat> patRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PAT);
        Repository<String, Pacient> pacientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PAT);
        Service service = new Service(patRepo, pacientRepo);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        PatController patController = fxmlLoader.getController();
        patController.setService(service);

        stage.setTitle("pat");
        stage.setScene(scene);
        stage.show();



        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("pacienti-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 550, 400);
        Stage stage2 = new Stage();
        PacientiController pacientiController = fxmlLoader2.getController();
        pacientiController.setService(service);
        stage2.setTitle("Pacienti");
        stage2.setScene(scene2);
        stage2.show();
    }

    public static void main(String[] args) {
        launch();
    }
}