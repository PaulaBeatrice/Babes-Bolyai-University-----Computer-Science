package com.example.l6.socialnetwork.controller;

import com.example.l6.socialnetwork.Domain.*;
import com.example.l6.socialnetwork.repository.Repository;
import com.example.l6.socialnetwork.repository.database.AccountDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.FriendRequestDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.FriendshipDatabaseRepository;
import com.example.l6.socialnetwork.repository.database.UserDatabaseRepository;
import com.example.l6.socialnetwork.service.FriendshipService;
import com.example.l6.socialnetwork.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.l6.socialnetwork.Domain.validators.AccountValidator;
import com.example.l6.socialnetwork.Domain.validators.FriendRequestValidator;
import com.example.l6.socialnetwork.Domain.validators.FriendshipValidator;
import com.example.l6.socialnetwork.Domain.validators.UserValidator;
import com.example.l6.socialnetwork.MyException;
import com.example.l6.socialnetwork.event.UserEvent;
import com.example.l6.socialnetwork.observer.Observer;
import com.example.l6.socialnetwork.service.FriendshipRequestService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HomeController implements Observer<UserEvent> {
    static User activeUser;
    public Label welcomeMessage;
    public Button buttonSendFriendRequest;
    public Button buttonRemoveFriend;
    public Button buttonRequests;

    public TableView<User> friendsTable;
    public TableColumn<User, String> friendColumnFirstName;
    public TableColumn <User, String> friendColumnLastName;
    public TableColumn <User,String> friendColumnEmail;
    public TableView <User> usersTable;
    public TableColumn <User, String> userColumnFirstName;
    public TableColumn <User, String> userColumnLastName;
    public TableColumn<User,String> userEmail;

    public Label messageToUser;
    public Button buttonRefresh;

    private UserService userService;
    private FriendshipRequestService friendRequestService;
    private FriendshipService friendshipService;
    ObservableList<User> modelUsers = FXCollections.observableArrayList();
    ObservableList<User> modelFriends = FXCollections.observableArrayList();

    static void setActiveUser(User activeUser) {
        HomeController.activeUser = activeUser;
    }

    public boolean unknownUser(User u){
        for(FriendRequest f: friendRequestService.getAll())
        {
            if(f.getFrom().equals(u.getId()) && f.getTo().equals(activeUser.getId()) && !(f.getStatus().equals(Status.REJECTED)))
                return false;
            if(f.getFrom().equals(activeUser.getId()) && f.getTo().equals(u.getId()) && !(f.getStatus().equals(Status.REJECTED)))
                return false;
        }
        return true;
    }

    private void initUsers()
    {
        Iterable<User> allUsers = userService.getAll();
        List<User> userList = StreamSupport.stream(allUsers.spliterator() , false)
                .filter(x -> (!x.getId().equals(activeUser.getId())))
                .filter(x -> friendshipService.findFriendship(x.getId(),activeUser.getId())==null)
                .filter(x -> friendshipService.findFriendship(activeUser.getId(),x.getId())==null)
                .filter(this::unknownUser)
                .collect(Collectors.toList());
        modelUsers.setAll(userList);
    }

    public void initFriends()
    {
        Map<User, LocalDateTime> allFriends = userService.getFriends(activeUser.getId());
        List<User> friendsList = new ArrayList<>();
        for (Map.Entry<User,LocalDateTime> entry : allFriends.entrySet())
            friendsList.add(entry.getKey());
        modelFriends.setAll(friendsList);
    }


    @FXML
    public void initialize()
    {
        String url = "jdbc:postgresql://localhost:5432/network_lab";
        String password = "postgress";
        String username = "postgres";

        Repository<Long, User> userDatabaseRepository = new UserDatabaseRepository(url, username, password);
        Repository<Tuple<Long, Long>, Friendship> friendshipDatabaseRepository = new FriendshipDatabaseRepository(url, username, password);
        Repository<String, Account> accountDatabaseRepository = new AccountDatabaseRepository(url, username, password);
        Repository<Tuple<Long, Long>, FriendRequest> friendRequestDatabaseRepository = new FriendRequestDatabaseRepository(url, username, password);

        this.userService = new UserService(userDatabaseRepository, friendshipDatabaseRepository, accountDatabaseRepository, new UserValidator(), new AccountValidator());
        this.friendRequestService = new FriendshipRequestService(friendRequestDatabaseRepository, friendshipDatabaseRepository,userDatabaseRepository , new FriendRequestValidator());
        this.friendshipService = new FriendshipService(userDatabaseRepository, friendRequestDatabaseRepository,friendshipDatabaseRepository, new FriendshipValidator());
        userService.addObserver(this);

        friendColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        friendColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        friendColumnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        userColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        userColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        userEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        usersTable.setItems(modelUsers);
        initUsers();

        friendsTable.setItems(modelFriends);
        initFriends();

        welcomeMessage.setText("Welcome, " + activeUser.getFirstName() + " " + activeUser.getLastName());
        welcomeMessage.setWrapText(true);
    }

    public void handleSendFriendRequest(ActionEvent actionEvent)
    {
        User selected = (User) usersTable.getSelectionModel().getSelectedItem();
        if (selected != null)
        {
            FriendRequest friendRequest = new FriendRequest(activeUser.getId(), selected.getId());
            try
            {
                this.friendRequestService.addFriendRequest(friendRequest);
                initUsers();
                this.messageToUser.setText("Friend request sent successfully!");
                this.messageToUser.setTextFill(Color.DARKGREEN);
            }catch (MyException e)
            {
                this.messageToUser.setText(e.getMessage());
                this.messageToUser.setTextFill(Color.RED);
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                     InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
                e.printStackTrace();
            }
        }
        else
        {
            this.messageToUser.setText("No item selected!");
            this.messageToUser.setTextFill(Color.DARKRED);
        }
    }

    public void handleRemoveFriend(ActionEvent actionEvent)
    {
        User selected = (User) friendsTable.getSelectionModel().getSelectedItem();
        if (selected != null)
        {
            Friendship friendship = new Friendship();
            friendship.setId(new Tuple<>(activeUser.getId(), selected.getId()));
            try
            {
                Friendship deleted = friendshipService.removeFriendship(friendship);
                initFriends();
                initUsers();
                this.messageToUser.setText("Friend removed successfully!");
                this.messageToUser.setTextFill(Color.DARKGREEN);
            }catch (MyException e)
            {
                this.messageToUser.setText(e.getMessage());
                this.messageToUser.setTextFill(Color.DARKRED);
            }
        }
        else
        {
            this.messageToUser.setText("No item selected!");
            this.messageToUser.setTextFill(Color.DARKRED);
        }
    }

    public void handleRequests(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/FriendRequests.fxml"));
            GridPane root = loader.load();
            FriendRequestController controller = loader.getController();
            this.friendRequestService.addObserver(controller);
            Scene scene = new Scene(root, 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Friend requests");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/images/request.png"));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserEvent userEvent)
    {
        initUsers();
        initFriends();
    }

    public void handleRefresh(ActionEvent actionEvent)
    {
        initFriends();
        initUsers();
    }
}
