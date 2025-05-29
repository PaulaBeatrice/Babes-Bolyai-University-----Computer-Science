package com.example.model1;

import com.example.model1.domains.*;
import com.example.model1.repos.*;
import com.example.model1.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private static String[] arguments;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//        MainPage mainPage = fxmlLoader.getController();
        Repository<Double, Location> locationRepository = new locationRepo<>();
        Repository<Double, Hotel> hotelRepository = new HotelRepo<>();
        Repository<Double, SpecialOffer> specialOfferRepository = new SpecialOfferRepo<>();
        Repository<Long, Client> clientRepository = new ClientRepo<>();
        Repository<Double, Reservation> repositoryRepository = new ReservationRepo<>();
        Service service = new Service(locationRepository, hotelRepository, specialOfferRepository, clientRepository, repositoryRepository);
//        mainPage.setService(new Service(locationRepository, hotelRepository));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        for (int i = 0; i < arguments.length; i++) {
            Stage stage1 = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
            Parent fxmLoader = loader.load();
            ClientController clientController = loader.getController();
            Scene scene1 = new Scene(fxmLoader, 800, 400);
            stage1.setTitle(arguments[i]);
            clientController.setId(Long.parseLong(stage1.getTitle()));
            clientController.setService(service);
            stage1.setScene(scene1);
            stage1.show();
        }


    }

    public static void setArg(String[] arg) {
        arguments = arg;
    }

    public static void main(String[] args) {
        setArg(args);
        launch();
    }
}