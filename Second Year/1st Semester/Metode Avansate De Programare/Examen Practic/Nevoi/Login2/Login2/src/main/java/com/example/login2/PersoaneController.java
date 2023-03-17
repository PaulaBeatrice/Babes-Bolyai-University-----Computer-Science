package com.example.login2;

import com.example.login2.domain.Nevoie;
import com.example.login2.domain.Persoana;
import com.example.login2.domain.TipuriOrase;
import com.example.login2.repository.Repository;
import com.example.login2.repository.factory.RepositoryEntity;
import com.example.login2.repository.factory.RepositoryFactory;
import com.example.login2.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class PersoaneController implements Initializable {
    @FXML
    private TableColumn<Nevoie, LocalDateTime> deadlineYou;

    @FXML
    private TableColumn<Nevoie, String> descrYou;

    @FXML
    private TableColumn<Nevoie, Integer> idYou;

    @FXML
    private TableView<Nevoie> nevoiTabel;

    @FXML
    private TableColumn<Nevoie, Integer> om_nevYou;

    @FXML
    private TableColumn<Nevoie, Integer> om_salvYou;

    @FXML
    private TableColumn<Nevoie, String> statusYou;

    @FXML
    private TableColumn<Nevoie, String> titluYou;


    String username_c;
    public void setUsername(String username) {
        this.username_c = username;
       // text.setText(username_c);
        init();
    }


    ObservableList<Nevoie> lista2 = FXCollections.observableArrayList();


    @FXML
    private Label mesaj;

    void init(){
        //System.out.printf(text.getText());
        //text.setText(username_c);
        id.setCellValueFactory(new PropertyValueFactory<Nevoie,Integer>("id"));
        titlu.setCellValueFactory(new PropertyValueFactory<Nevoie,String>("titlu"));
        descriere.setCellValueFactory(new PropertyValueFactory<Nevoie,String>("descriere"));
        deadline.setCellValueFactory(new PropertyValueFactory<Nevoie,LocalDateTime>("deadline"));
        om_in_nevoie.setCellValueFactory(new PropertyValueFactory<Nevoie,Integer>("ominNevoie"));
        om_salvator.setCellValueFactory(new PropertyValueFactory<Nevoie,Integer>("onSalvator"));
        status.setCellValueFactory(new PropertyValueFactory<Nevoie,String>("status"));
        Persoana pers = null;
        for(Persoana p: service.getAllPersoane())
        {
            if(p.getUsername().equals(username_c))
                pers = p;
        }
        for(Nevoie n:service.getAllNevoi())
        {
            assert pers != null;
            if(!Objects.equals(n.getOminNevoie(), pers.getId()))
            {
                for(Persoana p: service.getAllPersoane())
                    if(n.getOminNevoie().equals(p.getId()) && p.getOras().equals(pers.getOras()))
                        if(n.getOnSalvator() == null || n.getOnSalvator() == 0)
                            list.add(n);

            }
        }

        table.setItems(list);


        // -----------------TABEL CU FAPTE BUNE INIT ----------
        idYou.setCellValueFactory(new PropertyValueFactory<Nevoie,Integer>("id"));
        titluYou.setCellValueFactory(new PropertyValueFactory<Nevoie,String>("titlu"));
        descrYou.setCellValueFactory(new PropertyValueFactory<Nevoie,String>("descriere"));
        deadlineYou.setCellValueFactory(new PropertyValueFactory<Nevoie,LocalDateTime>("deadline"));
        om_nevYou.setCellValueFactory(new PropertyValueFactory<Nevoie,Integer>("ominNevoie"));
        om_salvYou.setCellValueFactory(new PropertyValueFactory<Nevoie,Integer>("onSalvator"));
        statusYou.setCellValueFactory(new PropertyValueFactory<Nevoie,String>("status"));
        for(Nevoie n:service.getAllNevoi())
        {
            assert pers != null;
            if(Objects.equals(n.getOnSalvator(), pers.getId()))
                lista2.add(n);
        }

        nevoiTabel.setItems(lista2);
    }


    @FXML
    private TableColumn<Nevoie, LocalDateTime> deadline;

    @FXML
    private TableColumn<Nevoie, String > descriere;

    @FXML
    private Tab doresc_sa_ajut;

    @FXML
    private Tab doresc_sa_fiu_ajutat;

    @FXML
    private TableColumn<Nevoie, Integer> id;

    @FXML
    private TableColumn<Nevoie, Integer> om_in_nevoie;

    @FXML
    private TableColumn<Nevoie, Integer> om_salvator;

    @FXML
    private TableColumn<Nevoie, String> status;

    @FXML
    private TableView<Nevoie> table;

    @FXML
    private TableColumn<Nevoie, String> titlu;
    @FXML
    private Text text;

    Repository<Integer, Persoana> localitatiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.PERSOANE);
    Repository<Integer, Nevoie> nevoiRepository = RepositoryFactory.getInstance().createRepository(RepositoryEntity.NEVOI);

    Service service = new Service(localitatiRepository,nevoiRepository);
    ObservableList<Nevoie> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void alegeNevoie(MouseEvent event) {

        int id = table.getSelectionModel().getSelectedItem().getId();
        String titlu = table.getSelectionModel().getSelectedItem().getTitlu();
        String descr = table.getSelectionModel().getSelectedItem().getDescriere();
        LocalDateTime data = table.getSelectionModel().getSelectedItem().getDeadline();
        int om_nev = table.getSelectionModel().getSelectedItem().getOminNevoie();
        int salv = 0;
        for(Persoana p : service.getAllPersoane())
            if(p.getUsername().equals(username_c))
                salv = p.getId();
        String status = "erou salvator";
        Nevoie n = new Nevoie(id,titlu,descr,data,om_nev,salv,status);
        service.updateNevoie(n);
        mesaj.setText("Nevoia a fost atribuita!");
    }

    //---------------- DORESC SA FIU AJUTAT --------------


    @FXML
    private DatePicker deadlinePicker;

    @FXML
    private TextField descriereCerere;

    @FXML
    private TextField idCerere;

    @FXML
    private TextField titluCerere;

    @FXML
    private Button trimiteCerere;


    @FXML
    void inregistreazaNevoia(MouseEvent event) {
        Persoana pers = null;
        for(Persoana p: service.getAllPersoane())
        {
            if(p.getUsername().equals(username_c))
                pers = p;
        }
        Nevoie nev = new Nevoie(Integer.valueOf(idCerere.getText()),titluCerere.getText(),descriereCerere.getText(),deadlinePicker.getValue().atStartOfDay(),pers.getId(),0,"caut erou");
        service.add(nev);
    }
}
