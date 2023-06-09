package com.example.l6.socialnetwork.controller;


import com.example.l6.socialnetwork.repository.Repository;
import com.example.l6.socialnetwork.repository.database.AccountDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.FriendshipDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.UserDatabaseRepository;
import com.example.l6.socialnetwork.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.example.l6.socialnetwork.Domain.Account;
import com.example.l6.socialnetwork.Domain.Friendship;
import com.example.l6.socialnetwork.Domain.Tuple;
import com.example.l6.socialnetwork.Domain.User;
import com.example.l6.socialnetwork.Domain.validators.AccountValidator;
import com.example.l6.socialnetwork.Domain.validators.UserValidator;
import com.example.l6.socialnetwork.Domain.validators.ValidationException;
import com.example.l6.socialnetwork.MyException;

import java.io.IOException;

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Label messageToUser;
    public UserService userService;

    @FXML
    public void initialize()
    {
        String url = "jdbc:postgresql://localhost:5432/network_lab";
        String password = "postgress";
        String username = "postgres";
//        final String url = ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.url");
//        final String username= ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.username");
//        final String password= ApplicationContext.getPROPERTIES().getProperty("database.socialnetwork.password");

        Repository<Long, User> userDatabaseRepository = new UserDatabaseRepository(url, username, password);
        Repository<Tuple<Long,Long>, Friendship> friendshipDatabaseRepository = new FriendshipDatabaseRepository(url, username,password);
        Repository<String, Account> accountDatabaseRepository = new AccountDatabaseRepository(url, username, password);

        this.userService = new UserService(userDatabaseRepository, friendshipDatabaseRepository, accountDatabaseRepository, new UserValidator(), new AccountValidator());

        usernameField.setText("");
        passwordField.setText("");
    }

    public void handleSubmitButtonAction(ActionEvent event){
        try{
            Account account = userService.findAccount(usernameField.getText());
            if(!account.getPassword().equals(passwordField.getText()))
            {
                messageToUser.setTextFill(Color.DARKRED);
                messageToUser.setText("Invalid password!");
                throw new ValidationException("Invalid password!");
            }


            HomeController.setActiveUser(userService.findUser(account.getUserID()));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Home.fxml"));
            GridPane root = loader.load();
            HomeController controller = loader.getController();
            Scene scene = new Scene(root, 650, 500);
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/images/home.png"));
            stage.setScene(scene);
            stage.show();
        }
        catch (MyException e) {
            messageToUser.setText(e.getMessage());
            messageToUser.setTextFill(Color.DARKRED);

            usernameField.setText("");
            passwordField.setText("");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleSignUpAction(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Signup.fxml"));
            GridPane root = loader.load();
            SignUpController controller = loader.getController();
            Scene scene = new Scene(root, 400, 400);
            Stage stage = new Stage();
            stage.setTitle("Sign up");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/images/login.png"));
            stage.setScene(scene);
            stage.show();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
