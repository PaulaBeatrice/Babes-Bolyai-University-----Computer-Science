package com.example.spital.controller;

import com.example.spital.domain.Pacient;
import com.example.spital.domain.Pat;
import com.example.spital.domain.TipPat;
import com.example.spital.domain.YesNo;
import com.example.spital.repository.Repository;
import com.example.spital.repository.factory.RepositoryEntity;
import com.example.spital.repository.factory.RepositoryFactory;
import com.example.spital.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PacientiController implements Initializable {
    public TextField tipPat;
    Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private TableColumn<Pacient, String> cnp;

    @FXML
    private TableColumn<Pacient, String> diagnostic;

    @FXML
    private TableColumn<Pacient, Integer> gravitate;

    @FXML
    private TableColumn<Pacient, YesNo> prematur;

    @FXML
    private TableView<Pacient> tabelPacienti;

    ObservableList<Pacient> list= FXCollections.observableArrayList();

    @FXML
    private TableColumn<Pacient, Integer> varsta;

    @FXML
    private Label mesajEroare;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Repository<Integer, Pat> patRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PAT);
        Repository<String, Pacient> pacientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PACIENT);
        Service sv = new Service(patRepo, pacientRepo);

        cnp.setCellValueFactory(new PropertyValueFactory<Pacient,String>("id"));
        diagnostic.setCellValueFactory(new PropertyValueFactory<Pacient,String>("diagnostic_pr"));
        gravitate.setCellValueFactory(new PropertyValueFactory<Pacient, Integer>("gravitate"));
        prematur.setCellValueFactory(new PropertyValueFactory<Pacient,YesNo>("prematur"));
        varsta.setCellValueFactory(new PropertyValueFactory<Pacient,Integer>("varsta"));

        for(Pacient p:sv.getAllPacienti())
        {
            int gasit = 0;
             for(Pat pat:sv.getAllPaturi())
                if(pat.getPacient_cnp()!=null && pat.getPacient_cnp().equals(p.getId()))
                    gasit = 1;
            if(gasit == 0)
                list.add(p);
        }

        List<Pacient> pacients = list;


        pacients = pacients.stream()
                .sorted(Comparator.comparingInt(p -> -p.getGravitate()))
                .collect(Collectors.toList());

        tabelPacienti.setItems(FXCollections.observableArrayList(pacients));
        tipPat.setText("");
    }


    @FXML
    void alegePat(MouseEvent event) {
        try {
            String cnp = tabelPacienti.getSelectionModel().getSelectedItem().getId();
            TipPat tip = TipPat.valueOf(tipPat.getText());
            Repository<Integer, Pat> patRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PAT);
            Repository<String, Pacient> pacientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PACIENT);
            Service sv = new Service(patRepo, pacientRepo);

            for (Pat p : sv.getAllPaturi()) {
                if (p.getPacient_cnp() == null && p.getTip_pat().equals(tip)) {
                    sv.updatePat(cnp, p.getId());
                    break;
                }
            }
            mesajEroare.setText("S-a efectuat mutarea");
        } catch (IllegalArgumentException e){
            mesajEroare.setText("Introduceti un tip de pat!");
        }

    }
}
