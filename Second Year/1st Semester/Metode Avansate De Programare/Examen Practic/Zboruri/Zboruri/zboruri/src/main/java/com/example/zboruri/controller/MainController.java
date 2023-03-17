package com.example.zboruri.controller;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Ticket;
import com.example.zboruri.domain.Zbor;
import com.example.zboruri.repository.Repository;
import com.example.zboruri.repository.factory.RepositoryEntity;
import com.example.zboruri.repository.factory.RepositoryFactory;
import com.example.zboruri.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Client client;

    @FXML
    private Label label;

    public void setClient(Client client) {
        this.client = client;
        label.setText("Bine ai venit" + client.getNume());
    }


    @FXML
    private ComboBox<String> comboPlecare;

    @FXML
    private ComboBox<String> comboSosire;

    @FXML
    private TableColumn<Zbor, Long> id;

    @FXML
    private DatePicker pickerData;

    @FXML
    private TableColumn<Zbor, LocalDateTime> land_time;

    @FXML
    private TableColumn<Zbor, Integer> seats;

    @FXML
    private TableView<Zbor> tabelZbor;


    @FXML
    private Button butonFiltrare;

    @FXML
    void filtreaza(MouseEvent event) {
        Repository<Integer, Client> clientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
        Repository<Long, Zbor> zborRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.ZBORURI);
        Repository<Integer, Ticket> ticketRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.TICKETE);
        Service service = new Service(clientRepo,zborRepo,ticketRepo);
        String from = comboPlecare.getValue();
        String to = comboSosire.getValue();
        LocalDateTime data = pickerData.getValue().atStartOfDay();
        //System.out.println("Ce dau" + from + " " + to + " " + data);
        for(Zbor z:service.getAllZboruri())
        {
           // System.out.println(z.getFrom() + " " + z.getTo() + " " + z.getDeparture_time());
            if(z.getFrom().equals(from) && z.getTo().equals(to)
                    && z.getDeparture_time().getYear() == data.getYear()
                    && z.getDeparture_time().getMonth() == data.getMonth()
                    && z.getDeparture_time().getDayOfMonth() == data.getDayOfMonth())
                list.add(z);
        }
        tabelZbor.setItems(list);
    }

    ObservableList<Zbor> list= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Zbor,Long>("id"));
        land_time.setCellValueFactory(new PropertyValueFactory<Zbor,LocalDateTime>("landing_time"));
        seats.setCellValueFactory(new PropertyValueFactory<Zbor,Integer>("seats"));

        ObservableList<String> options =
                FXCollections.observableArrayList("Paris", "Roma", "Havana");
        comboPlecare.setItems(options);
        comboSosire.setItems(options);
    }

    public void rezervaBilet(MouseEvent mouseEvent) {
        Repository<Integer, Client> clientRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.CLIENTI);
        Repository<Long, Zbor> zborRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.ZBORURI);
        Repository<Integer, Ticket> ticketRepo = RepositoryFactory.getInstance().createRepository(RepositoryEntity.TICKETE);
        Service service = new Service(clientRepo,zborRepo,ticketRepo);


        Zbor z = tabelZbor.getSelectionModel().getSelectedItem();
        String username = client.getUsername();
        Long idZbor = z.getId();
        Integer idTicket = 0;
        for(Ticket t:service.getAllTickete())
        {
            if(t.getId() > idTicket)
                idTicket = t.getId();
        }

        Ticket t = new Ticket(idTicket+1, username,idZbor,LocalDateTime.now());
        service.add(t);

        // ------- ACTUALIZARE ZBOR- NR LOCURI ------------
        Zbor new_zbor = new Zbor(z.getId(),z.getFrom(),z.getTo(),z.getDeparture_time(),z.getLanding_time(),z.getSeats()-1);
        service.update(new_zbor);


        // -- ACTUALIZARE TABEL ----
        list.clear();
        String from = comboPlecare.getValue();
        String to = comboSosire.getValue();
        LocalDateTime data = pickerData.getValue().atStartOfDay();
        for(Zbor zb:service.getAllZboruri())
        {
            if(z.getFrom().equals(from) && zb.getTo().equals(to)
                    && zb.getDeparture_time().getYear() == data.getYear()
                    && zb.getDeparture_time().getMonth() == data.getMonth()
                    && zb.getDeparture_time().getDayOfMonth() == data.getDayOfMonth())
                list.add(zb);
        }
        tabelZbor.setItems(list);
    }
}
