package controller;

import domain.Book;
import domain.BookRental;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RentalFormularController {
    private Service service;
    private List<BookRental> rentals;

    public void setService(Service service) {
        this.service = service;
    }

    public void setRentals(List<BookRental> rentals) {
        this.rentals = rentals;
    }

    @FXML
    private TextField txtDays;

    @FXML
    void finishRentalClick(MouseEvent event) throws IOException {
        if(Objects.equals(txtDays.getText(),"") ){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Introduceti nr de zile!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            int days = 0;
            try{
                days = Integer.parseInt(txtDays.getText());
                for(BookRental bookRental: rentals)
                {
                    bookRental.setDays(days);
                    service.borrowBook(bookRental);
                }
                Stage st = (Stage) txtDays.getScene().getWindow();
                st.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Imprumutul s-a efectuat cu succes!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                txtDays.setText("");
            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Nr. de zile e numeric!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}