package com.example.clinica.controller;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.service.Service;
import com.example.clinica.repository.Repository;
import com.example.clinica.repository.factory.RepositoryEntity;
import com.example.clinica.repository.factory.RepositoryFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MediciController implements Initializable {
    Service service;
    Medic medic;



    @FXML
    private TableView<Consultatie> consultatiiTableView;

    @FXML
    private TableColumn<Consultatie, LocalDateTime> data;

    @FXML
    private TableColumn<Consultatie, Integer> id;

    @FXML
    private TableColumn<Consultatie, String> nume_pacient;

    @FXML
    private TableColumn<Consultatie, Integer> ora;

    ObservableList<Consultatie> list= FXCollections.observableArrayList();


  //  public void setMedic(Medic medic) {
   //     this.medic = medic;
   // }

    public void setService(Service service, Medic medic) {
        this.service = service;
        this.medic = medic;
       // System.out.println(medic.getId());
        init();

        id.setCellValueFactory(new PropertyValueFactory<Consultatie,Integer>("id"));
        nume_pacient.setCellValueFactory(new PropertyValueFactory<Consultatie,String>("numePacient"));
        data.setCellValueFactory(new PropertyValueFactory<Consultatie,LocalDateTime>("data"));
        ora.setCellValueFactory(new PropertyValueFactory<Consultatie,Integer>("ora"));

        consultatiiTableView.setItems(list);
    }

    public void init()
    {
       // System.out.println(medic.getId());
        Iterable<Consultatie> consultatii = service.getAllConsultatii();
//        List<Consultatie> consultatiiMedic = StreamSupport.stream(consultatii.spliterator() , false)
//                .filter(x -> (x.getIdMedic()==medic.getId()))
//                .collect(Collectors.toList());
//        consultatiiTableView.setItems((ObservableArray<Consultatie>) consultatiiMedic);
        for(Consultatie c:service.getAllConsultatii()){
            System.out.println(c.getId());
         //   System.out.println(medic.getId() + ' ' + c.getIdMedic());
            if(c.getIdMedic() == medic.getId())
            {
                list.add(c);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // System.out.println(medic.getId());
//        Repository<Integer, Sectie> sectiiRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.SECTIE);
//        Repository<Integer, Medic> mediciRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.MEDIC);
//        Repository<Integer, Consultatie> consultatieRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CONSULTATIE);
//        Service sv = new Service(sectiiRepo, mediciRepo,consultatieRepo);
//

//
//        for(Consultatie c:sv.getAllConsultatii()){
//            list.add(c);
//        }
//
//        consultatiiTableView.setItems(list);

    }
}
/*
package com.example.clinica.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MediciController {

    @FXML
    private TableView<?> consultatiiTableView;

    @FXML
    private TableColumn<?, ?> data;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> nume_pacient;

    @FXML
    private TableColumn<?, ?> ora;

}

 */
