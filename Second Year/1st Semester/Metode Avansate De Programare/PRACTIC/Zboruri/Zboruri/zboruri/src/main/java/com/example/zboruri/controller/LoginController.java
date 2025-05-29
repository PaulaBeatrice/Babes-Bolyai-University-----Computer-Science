package com.example.zboruri.controller;

import com.example.zboruri.HelloApplication;
import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Ticket;
import com.example.zboruri.domain.Zbor;
import com.example.zboruri.repository.Repository;
import com.example.zboruri.repository.factory.RepositoryEntity;
import com.example.zboruri.repository.factory.RepositoryFactory;
import com.example.zboruri.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button butonLogin;

    @FXML
    private TextField username;

    @FXML
    void logare(MouseEvent event) throws IOException {
        Repository<Integer, Client> clientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
        Repository<Long, Zbor> zborRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.ZBORURI);
        Repository<Integer, Ticket> ticketRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.TICKETE);
        Service service = new Service(clientRepo,zborRepo,ticketRepo);
       // String username = null;
        for(Client c : service.getAllClienti())
            if(c.getUsername().equals(username.getText()))
            {
                FXMLLoader loaderMain = new FXMLLoader((HelloApplication.class.getResource("main-view.fxml")));
                Stage mainStage = new Stage();
                Scene mainScene;
                mainScene = new Scene(loaderMain.load(),500,400);
                MainController mainController = loaderMain.getController();
                mainController.setClient(c);
                mainStage.setTitle("Login");
                mainStage.setScene(mainScene);
                mainStage.show();
//                FXMLLoader medic_loader = new FXMLLoader(HelloApplication.class.getResource("medici-view.fxml"));
//                Stage medic_stage = new Stage();
//                Scene medic_scene;
//                try {
//                    medic_scene = new Scene(medic_loader.load(), 500, 400);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                MediciController mediciController = medic_loader.getController();
//                mediciController.setService(service,medic);
            }
    }

}
