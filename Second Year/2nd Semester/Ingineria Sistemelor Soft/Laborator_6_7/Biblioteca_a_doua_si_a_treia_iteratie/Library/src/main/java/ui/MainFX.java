package ui;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.orm.hibernate.BookRentalRepository;
import repository.orm.hibernate.BookRepository;
import repository.orm.hibernate.ClientRepository;
import repository.orm.hibernate.LibrarianRepository;
import service.Service;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BookRepository bookRepository = new BookRepository();
        ClientRepository clientRepository = new ClientRepository();
        LibrarianRepository librarianRepository = new LibrarianRepository();
        BookRentalRepository bookRentalRepository = new BookRentalRepository();

        Service service = new Service(bookRepository,clientRepository,librarianRepository, bookRentalRepository);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Parent root = loader.load();
            LoginController ctrl = loader.getController();
            ctrl.setService(service);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hello");
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
