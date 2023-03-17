package com.example.anar;

import com.example.anar.controller.LocalitatiController;
import com.example.anar.controller.RauController;
import com.example.anar.controller.Risc;
import com.example.anar.domain.Localitate;
import com.example.anar.domain.Rau;
import com.example.anar.repository.Repository;
import com.example.anar.repository.factory.RepositoryEntity;
import com.example.anar.repository.factory.RepositoryFactory;
import com.example.anar.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Repository<Integer, Rau> rauRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.RAURI);
        Repository<Integer, Localitate> locRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.LOCALITATI);
        Service service = new Service(rauRepo,locRepo);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rau-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        RauController rauController = fxmlLoader.getController();
        rauController.setService(service);

        stage.setTitle("Rauri");
        stage.setScene(scene);
        stage.show();

        // FERESTRELE CU LOCALITATI SI RISC
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("localitati-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 550, 400);
        Stage stage2 = new Stage();
        LocalitatiController localitatiController = fxmlLoader2.getController();
        localitatiController.setRisc(Risc.REDUS,service);
        stage2.setTitle("Risc Redus");
        stage2.setScene(scene2);
        stage2.show();


        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("localitati-view.fxml"));
        Scene scene3 = new Scene(fxmlLoader3.load(), 550, 400);
        Stage stage3 = new Stage();
        LocalitatiController localitatiController3 = fxmlLoader3.getController();
        localitatiController3.setRisc(Risc.MEDIU,service);
        stage3.setTitle("Risc Mediu");
        stage3.setScene(scene3);
        stage3.show();

        FXMLLoader fxmlLoader4= new FXMLLoader(HelloApplication.class.getResource("localitati-view.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 550, 400);
        Stage stage4 = new Stage();
        LocalitatiController localitatiController4 = fxmlLoader4.getController();
        localitatiController4.setRisc(Risc.MAJOR,service);
        stage4.setTitle("Risc Major");
        stage4.setScene(scene4);
        stage4.show();
    }

    public static void main(String[] args) {
        launch();
    }
}