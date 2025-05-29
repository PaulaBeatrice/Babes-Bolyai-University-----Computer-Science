package com.example.login2;

import com.example.login2.domain.Nevoie;
import com.example.login2.domain.Persoana;
import com.example.login2.domain.TipuriOrase;
import com.example.login2.repository.NevoiRepository;
import com.example.login2.repository.Repository;
import com.example.login2.repository.factory.RepositoryEntity;
import com.example.login2.repository.factory.RepositoryFactory;
import com.example.login2.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.FXPermission;

import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.*;

public class InregistrareController implements Initializable {

    @FXML
    private Button buton_inregistrare;

    @FXML
    private ComboBox<String> comboBox_oras;

    @FXML
    private Button login;

    @FXML
    private TextField nr_strada;

    @FXML
    private TextField nume;

    @FXML
    private TextField parola;

    @FXML
    private TextField prenume;

    @FXML
    private TextField strada;

    @FXML
    private TextField telefon;

    @FXML
    private TextField username;
    @FXML
    private ListView<String> lista;
    @FXML
    private Text usename;
    @FXML
    void login(MouseEvent event) throws IOException {
        usename.setText(lista.getSelectionModel().getSelectedItem());



        FXMLLoader fxmlLoader4= new FXMLLoader(HelloApplication.class.getResource("persoane-view.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 600, 650);
        Stage stage4 = new Stage();
        PersoaneController localitatiController4 = fxmlLoader4.getController();
        localitatiController4.setUsername(lista.getSelectionModel().getSelectedItem());
        stage4.setTitle(lista.getSelectionModel().getSelectedItem());
        stage4.setScene(scene4);
        stage4.show();

    }
    Repository<Integer, Persoana> localitatiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PERSOANE);
    Repository<Integer, Nevoie> nevoiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.NEVOI);

    Service service = new Service(localitatiRepository,nevoiRepository);
    @FXML
    void inregistrare(MouseEvent event) throws IOException {
        Integer max = 0;
        for(Persoana pers:service.getAllPersoane())
        {
            if(pers.getId()> max)
                max = pers.getId();
        }
        Persoana p = new Persoana(max+1,nume.getText(),prenume.getText(),username.getText(),parola.getText(),TipuriOrase.valueOf(comboBox_oras.getSelectionModel().getSelectedItem()),strada.getText(),nr_strada.getText(),telefon.getText());
        service.save(p);


        FXMLLoader fxmlLoader4= new FXMLLoader(HelloApplication.class.getResource("persoane-view.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 550, 400);
        Stage stage4 = new Stage();
        PersoaneController localitatiController4 = fxmlLoader4.getController();
        localitatiController4.setUsername(p.getUsername());
        stage4.setTitle(p.getUsername());
        stage4.setScene(scene4);
        stage4.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add(String.valueOf(TipuriOrase.Bistrita));
        list.add(String.valueOf(TipuriOrase.Cluj));
        list.add(String.valueOf(TipuriOrase.Sibiu));
        list.add(String.valueOf(TipuriOrase.Brasov));
        comboBox_oras.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList();
        for(Persoana p:service.getAllPersoane())
        {
            list2.add(p.getUsername());
        }
        lista.setItems(list2);
    }
}
