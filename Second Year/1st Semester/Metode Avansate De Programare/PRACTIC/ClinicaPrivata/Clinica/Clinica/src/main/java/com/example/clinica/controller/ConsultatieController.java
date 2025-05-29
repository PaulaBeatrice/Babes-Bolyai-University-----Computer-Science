package com.example.clinica.controller;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.Repository;
import com.example.clinica.repository.factory.RepositoryEntity;
import com.example.clinica.repository.factory.RepositoryFactory;
import com.example.clinica.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ConsultatieController implements Initializable {
    Service service;
    @FXML
    private DatePicker picker_data;

    @FXML
    private Spinner<Integer> spinner_ora;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(8,20,12);

    @FXML
    private TextField text_cnp;

    @FXML
    private TextField text_id;

    @FXML
    private TextField text_medic;

    @FXML
    private TextField text_nume;

    @FXML
    void inregistrareProgramare(MouseEvent event) {
        LocalDateTime time = picker_data.getValue().atStartOfDay();
        Consultatie c = new Consultatie(Integer.valueOf(text_id.getText()) , Integer.valueOf(text_medic.getText()) , text_cnp.getText() , text_nume.getText() , time ,spinner_ora.getValue());
        service.add(c);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spinner_ora.setValueFactory(svf);
        Repository<Integer, Sectie> sectiiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.SECTIE);
        Repository<Integer, Medic> mediciRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.MEDIC);
        Repository<Integer, Consultatie> cosultatiiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CONSULTATIE);
        this.service = new Service(sectiiRepository,mediciRepository,cosultatiiRepository);
    }
}
