package controller;

import domain.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import service.Service;

public class ClientController {
    private Client client;
    public void setClient(Client client){
        this.client = client;
        welcomeLabel.setText("Welcome "+ client.getFirst_name() + " " + client.getLast_name());
    }

    private Service service;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private Label welcomeLabel;

    @FXML
    void clickRentBook(MouseEvent event) {

    }

    @FXML
    void clickReturnBook(MouseEvent event) {

    }

    @FXML
    void clickViewRental(MouseEvent event) {

    }

}
