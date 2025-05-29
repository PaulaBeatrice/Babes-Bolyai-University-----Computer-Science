package com.example.clinica.controller;

import com.example.clinica.HelloApplication;
import com.example.clinica.domain.Sectie;
import com.example.clinica.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SectiiController {

    Service service;

    @FXML
    TableView<Sectie> sectiiTableView;

    public TableColumn<Sectie, Integer>  id;
    public TableColumn<Sectie, String>  nume;
    public TableColumn<Sectie, Integer> sef;
    public TableColumn<Sectie, Integer> pret;
    public TableColumn<Sectie, Integer> durata;

    ObservableList<Sectie> list= FXCollections.observableArrayList();


    public void setService(Service service) {
        this.service = service;

        initSectii();
    }


    public void initSectii()
    {
        id.setCellValueFactory(new PropertyValueFactory<Sectie,Integer>("id"));
        nume.setCellValueFactory(new PropertyValueFactory<Sectie,String>("nume"));
        sef.setCellValueFactory(new PropertyValueFactory<Sectie,Integer>("idSefDeSectie"));
        pret.setCellValueFactory(new PropertyValueFactory<Sectie,Integer>("pretPerConsulattie"));
        durata.setCellValueFactory(new PropertyValueFactory<Sectie,Integer>("durataMaximaConsultatie"));
        for(Sectie sectie:service.getAllSectii()){
            list.add(sectie);
        }

        sectiiTableView.setItems(list);
    }

    public void alegeSectie(MouseEvent mouseEvent) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("consultatie-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),400,400);
        stage.setTitle("Programare");
        stage.setScene(scene);
        stage.show();
    }
}
