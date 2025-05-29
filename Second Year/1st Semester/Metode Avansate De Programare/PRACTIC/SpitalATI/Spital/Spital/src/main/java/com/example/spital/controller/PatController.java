package com.example.spital.controller;

import com.example.spital.domain.Pacient;
import com.example.spital.domain.Pat;
import com.example.spital.domain.TipPat;
import com.example.spital.repository.Repository;
import com.example.spital.repository.factory.RepositoryEntity;
import com.example.spital.repository.factory.RepositoryFactory;
import com.example.spital.service.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PatController implements Initializable {
    Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @FXML
    private Label nrOcupate;

    @FXML
    private Label nrTIC;

    @FXML
    private Label nrTIIP;

    @FXML
    private Label nrTIM;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Repository<Integer, Pat> patRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PAT);
        Repository<String, Pacient> pacientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PAT);
        Service sv = new Service(patRepo, pacientRepo);
        int nrO = 0, nrTic = 0, nrTiip = 0, nrTim = 0;
        for(Pat p : sv.getAllPaturi())
        {
            if(p.getPacient_cnp()== null) {
                if (p.getTip_pat().equals(TipPat.TIC))
                    nrTic++;
                if (p.getTip_pat().equals(TipPat.TIIP))
                    nrTiip++;
                if (p.getTip_pat().equals(TipPat.TIM))
                    nrTim++;
            }
            else
            {
                nrO++;
            }
        }
        nrOcupate.setText(String.valueOf(nrO));
        nrTIC.setText(String.valueOf(nrTic));
        nrTIIP.setText(String.valueOf(nrTiip));
        nrTIM.setText(String.valueOf(nrTim));
    }
}
