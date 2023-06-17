package controller;

import domain.Book;
import domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

public class ViewClientsController {
    private Service service;
    public void setService(Service service){
        this.service = service;

        getClients();

    }

    ObservableList<Client> clients = FXCollections.observableArrayList();

    public void getClients(){
        idClm.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        usernameClm.setCellValueFactory(new PropertyValueFactory<Client,String>("username"));
        passwlm.setCellValueFactory(new PropertyValueFactory<Client,String>("password"));
        fnameClm.setCellValueFactory(new PropertyValueFactory<Client,String>("first_name"));
        lnameClm.setCellValueFactory(new PropertyValueFactory<Client,String>("last_name"));
        emailClm.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
        phoneClm.setCellValueFactory(new PropertyValueFactory<Client,String>("phone_number"));
        for(Client client: service.getAllClients())
            clients.add(client);
        tabelClienti.setItems(clients);
    }

    @FXML
    private TableView<Client> tabelClienti;

    @FXML
    private TableColumn<Client, Integer> idClm;

    @FXML
    private TableColumn<Client, String> usernameClm;

    @FXML
    private TableColumn<Client, String> passwlm;

    @FXML
    private TableColumn<Client, String> fnameClm;

    @FXML
    private TableColumn<Client, String> lnameClm;

    @FXML
    private TableColumn<Client, String> emailClm;

    @FXML
    private TableColumn<Client, String> phoneClm;

}
