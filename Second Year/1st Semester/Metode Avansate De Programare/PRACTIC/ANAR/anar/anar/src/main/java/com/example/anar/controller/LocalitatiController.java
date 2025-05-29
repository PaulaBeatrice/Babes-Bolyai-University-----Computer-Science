package com.example.anar.controller;

import com.example.anar.domain.Localitate;
import com.example.anar.domain.Rau;
import com.example.anar.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Objects;

public class LocalitatiController {
    Risc risc;
    Service service;
    public void setRisc(Risc r, Service s){
        this.risc = r;
        this.service = s;
        init();

        tabelLocalitati.setItems(list);
    }

    @FXML
    private TableColumn<Localitate, Integer> cota_max;

    @FXML
    private TableColumn<Localitate, Integer> cota_min;

    @FXML
    private TableColumn<Localitate, String> nume;

    @FXML
    private TableColumn<Localitate, Integer> rau;

    @FXML
    private TableView<Localitate> tabelLocalitati;

    ObservableList<Localitate> list= FXCollections.observableArrayList();

    public void init(){
        nume.setCellValueFactory(new PropertyValueFactory<Localitate,String>("nume"));
        rau.setCellValueFactory(new PropertyValueFactory<Localitate,Integer>("rau"));
        cota_min.setCellValueFactory(new PropertyValueFactory<Localitate, Integer>("cota_minima"));
        cota_max.setCellValueFactory(new PropertyValueFactory<Localitate,Integer>("cota_maxima"));
        if(risc.equals(Risc.REDUS))
        {
            for(Localitate l : service.getAllLocalitati())
            {
                int cotaRau = 0;
                for(Rau r: service.getAllRauri())
                    if(Objects.equals(r.getId(), l.getRau()))
                        cotaRau = r.getCota_medie();
                if(cotaRau < l.getCota_minima())
                    list.add(l);
            }
        }

        if(risc.equals(Risc.MEDIU))
        {
            for(Localitate l : service.getAllLocalitati())
            {
                int cotaRau = 0;
                for(Rau r: service.getAllRauri())
                    if(Objects.equals(r.getId(), l.getRau()))
                        cotaRau = r.getCota_medie();
                if(cotaRau > l.getCota_minima() && cotaRau < l.getCota_maxima())
                    list.add(l);
            }
        }

        if(risc.equals(Risc.MAJOR))
        {
            for(Localitate l : service.getAllLocalitati())
            {
                int cotaRau = 0;
                for(Rau r: service.getAllRauri())
                    if(Objects.equals(r.getId(), l.getRau()))
                        cotaRau = r.getCota_medie();
                if(cotaRau > l.getCota_maxima())
                    list.add(l);
            }
        }

    }

}
