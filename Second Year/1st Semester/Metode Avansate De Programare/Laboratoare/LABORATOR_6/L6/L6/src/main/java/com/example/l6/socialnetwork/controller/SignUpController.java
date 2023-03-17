package com.example.l6.socialnetwork.controller;

import com.example.l6.socialnetwork.repository.Repository;
import com.example.l6.socialnetwork.repository.database.AccountDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.FriendshipDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.UserDatabaseRepository;
import com.example.l6.socialnetwork.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import com.example.l6.socialnetwork.Domain.Account;
import com.example.l6.socialnetwork.Domain.Friendship;
import com.example.l6.socialnetwork.Domain.Tuple;
import com.example.l6.socialnetwork.Domain.User;
import com.example.l6.socialnetwork.Domain.validators.AccountValidator;
import com.example.l6.socialnetwork.Domain.validators.UserValidator;
import com.example.l6.socialnetwork.MyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignUpController {
    public TextField usernameField;
    public PasswordField passwordField1;
    public PasswordField passwordField2;
    public UserService userService;
    public TextField firstNameField;
    public TextField lastNameField;
    public Label messageToUser;

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
        Repository<Tuple<Long, Long>, Friendship> friendshipDatabaseRepository = new FriendshipDatabaseRepository(url, username, password);
        Repository<String, Account> accountDatabaseRepository = new AccountDatabaseRepository(url, username, password);

        this.userService = new UserService(userDatabaseRepository, friendshipDatabaseRepository, accountDatabaseRepository, new UserValidator(), new AccountValidator());

        this.usernameField.setText("");
        this.passwordField1.setText("");
        this.passwordField2.setText("");
        this.firstNameField.setText("");
        this.lastNameField.setText("");
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent)
    {
        String username = this.usernameField.getText();
        String password1 = this.passwordField1.getText();
        String password2 = this.passwordField2.getText();
        String firstName = this.firstNameField.getText();
        String lastName = this.lastNameField.getText();

        if(password1.compareTo(password2) != 0)
        {
            messageToUser.setText("Passwords do not match!");
            messageToUser.setTextFill(Color.DARKRED);

            this.passwordField1.setText("");
            this.passwordField2.setText("");
        }

        try
        {
            User user = new User(firstName, lastName, username);
            user.setId(this.userService.generateUserID());
            Account account = new Account(username, password1, user.getId());
            account.setId(username);

            this.userService.addUser(user, account);

            messageToUser.setText("Account created successfully!");
            messageToUser.setTextFill(Color.DARKGREEN);
        }catch (MyException e)
        {
            messageToUser.setText(e.getMessage());
            messageToUser.setTextFill(Color.DARKRED);
            this.firstNameField.setText("");
            this.lastNameField.setText("");
            this.usernameField.setText("");
            this.passwordField1.setText("");
            this.passwordField2.setText("");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
