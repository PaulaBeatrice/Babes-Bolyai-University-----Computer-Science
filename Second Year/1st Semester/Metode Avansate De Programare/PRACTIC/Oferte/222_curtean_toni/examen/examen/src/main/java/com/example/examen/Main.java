package com.example.examen;

import com.example.examen.controller.MainController;
import com.example.examen.repository.RepositoryHotel;
import com.example.examen.repository.RepositoryLocation;
import com.example.examen.repository.RepositorySpecialOffer;
import com.example.examen.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //repositorys;
    //service;

    RepositoryLocation repositoryLocation;
    RepositoryHotel repositoryHotel;

    RepositorySpecialOffer repositorySpecialOffer;

    Service service;

    @Override
    public void start(Stage primaryStage) throws Exception {
        repositoryLocation=new RepositoryLocation("postgres","Mioritmic03","jdbc:postgresql://localhost:5432/postgres");
        repositoryHotel=new RepositoryHotel("postgres","Mioritmic03","jdbc:postgresql://localhost:5432/postgres");
        repositorySpecialOffer=new RepositorySpecialOffer("postgres","Mioritmic03","jdbc:postgresql://localhost:5432/postgres");
        service=new Service(repositoryHotel,repositoryLocation,repositorySpecialOffer);
        FXMLLoader loader=new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene=new Scene(loader.load(),600,360);
        primaryStage.setScene(scene);
        primaryStage.show();
        MainController mainController=loader.getController();
        mainController.setService(service);
        mainController.init();
    }


    public static void main(String[] args){
        launch(args);
    }
}
