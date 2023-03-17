package com.example.clinica;

import com.example.clinica.controller.MediciController;
import com.example.clinica.controller.SectiiController;
import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.Repository;
import com.example.clinica.repository.factory.RepositoryEntity;
import com.example.clinica.repository.factory.RepositoryFactory;
import com.example.clinica.repository.factory.RepositoryType;
import com.example.clinica.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Repository<Integer, Sectie> sectiiRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.SECTIE);
        Repository<Integer, Medic> mediciRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.MEDIC);
        Repository<Integer, Consultatie> consultatieRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CONSULTATIE);
        Service service = new Service(sectiiRepo, mediciRepo,consultatieRepo);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sectii-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);

        SectiiController sectiiController = fxmlLoader.getController();
        sectiiController.setService(service);

        stage.setTitle("Sectii");
        stage.setScene(scene);
        stage.show();


        service.getAllMedici().forEach(medic -> {
            FXMLLoader medic_loader = new FXMLLoader(HelloApplication.class.getResource("medici-view.fxml"));
            Stage medic_stage = new Stage();
            Scene medic_scene;
            try {
                medic_scene = new Scene(medic_loader.load(), 500, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            MediciController mediciController = medic_loader.getController();
            mediciController.setService(service,medic);
            //mediciController.setMedic(medic);



            medic_stage.setTitle("Medic " + medic.getId() + ")" + medic.getNume());
            medic_stage.setScene(medic_scene);
            medic_stage.show();
        });



    }

    public static void main(String[] args) {
        launch();
    }
}