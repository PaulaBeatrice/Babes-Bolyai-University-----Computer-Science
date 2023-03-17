package com.example.anar.controller;

import com.example.anar.domain.Localitate;
import com.example.anar.domain.Rau;
import com.example.anar.repository.Repository;
import com.example.anar.repository.factory.RepositoryEntity;
import com.example.anar.repository.factory.RepositoryFactory;
import com.example.anar.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class RauController implements Initializable {
    @FXML
    private Button butonModifca;

    @FXML
    private TableColumn<Rau, Integer> cota;

    @FXML
    private TableColumn<Rau, Integer> id;

    @FXML
    private TableColumn<Rau, String> nume;

    @FXML
    private TextField cotaNoua;

    @FXML
    private TableView<Rau> tabelRauri;

    Service service;
    ObservableList<Rau> listaR= FXCollections.observableArrayList();

    public void setService(Service service) {
        this.service = service;
        init();
    }

    public void init(){
        id.setCellValueFactory(new PropertyValueFactory<Rau,Integer>("id"));
        nume.setCellValueFactory(new PropertyValueFactory<Rau,String>("nume"));
        cota.setCellValueFactory(new PropertyValueFactory<Rau,Integer>("cota_medie"));

        for(Rau r:service.getAllRauri())
            listaR.add(r);
        tabelRauri.setItems(listaR);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void modifica(MouseEvent mouseEvent) {
        int idRau = tabelRauri.getSelectionModel().getSelectedItem().getId();
        service.modificaCotaRau(idRau, Integer.parseInt(cotaNoua.getText()));
    }
}
