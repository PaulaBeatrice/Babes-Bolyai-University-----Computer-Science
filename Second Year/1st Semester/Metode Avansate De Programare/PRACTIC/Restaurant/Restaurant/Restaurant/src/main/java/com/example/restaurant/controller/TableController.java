package com.example.restaurant.controller;

import com.example.restaurant.domain.Entity;
import com.example.restaurant.domain.MenuItem;
import com.example.restaurant.domain.OrderItems;
import com.example.restaurant.domain.OrderStatus;
import com.example.restaurant.domain.Table;
import com.example.restaurant.service.Service;
import com.example.restaurant.utils.observer.Observer;
import com.example.restaurant.utils.utils.OrderEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class TableController implements Observer<OrderEvent> {
    Service service;
    Table table;

    List<OrderItems> orderMenuItems = new ArrayList<>();
    @FXML
    ListView<CategoryItems> menuListView;

    @FXML
    ObservableList<CategoryItems> menuObservableList;

    @FXML
    Button placeOrderButton;

    @FXML
    Label orderStatusLabel = new Label();

    @FXML
    BorderPane mainPane;

    @FXML
    public void setTable(Table table) {
        this.table = table;
    }

    @FXML
    public void setService(Service service) {
        this.service = service;
        this.service.addOrderObservers(this);
        menuObservableList = FXCollections.observableArrayList();
        initMenu();
    }

    static class CategoryItemsCell extends ListCell<CategoryItems> {
        @FXML
        private BorderPane outerBox = new BorderPane();
        @FXML
        private VBox categoryBox = new VBox();
        @FXML
        private Label categoryLabel = new Label();

        @FXML
        private ListView<MenuItem> menuItemListView = new ListView<>();
        @FXML
        private ObservableList<MenuItem> itemsObservableList = FXCollections.observableArrayList();

        @FXML
        private VBox itemsBox = new VBox();
        @FXML
        private HBox dateBox = new HBox();
        @FXML
        private Label messageLabel = new Label();
        @FXML
        private Label dateLabel = new Label();

        public CategoryItemsCell(List<OrderItems> orderMenuItems, Button placeOrderButton) {
            categoryBox.getChildren().setAll(categoryLabel, menuItemListView);
            menuItemListView.setCellFactory(list -> {
                return new ListCell<MenuItem>() {
                    private HBox itemBox = new HBox();
                    private Label nameLabel = new Label();
                    private Label priceLabel = new Label();
                    private Pane pane1 = new Pane();
                    private Pane pane2 = new Pane();
                    private Pane pane3 = new Pane();

                    private CheckBox checkBox = new CheckBox();
                    private TextField quantityField = new TextField();

                    @Override
                    protected void updateItem(MenuItem menuItem, boolean empty) {
                        super.updateItem(menuItem, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            checkBox.disableProperty().setValue(true);
                            checkBox.selectedProperty().addListener(listener -> {
                                if(checkBox.isSelected()) {
                                    try{
                                        orderMenuItems.add(new OrderItems(menuItem, Integer.parseInt(quantityField.getText())));

                                    } catch (NumberFormatException numberFormatException) {
                                        MessageAlert.showErrorMessage((Stage)(checkBox.getScene().getWindow()), "Eroare valoare cantitate");
                                        checkBox.selectedProperty().setValue(false);
                                    }
                                    if(placeOrderButton.disableProperty().get()) {
                                        placeOrderButton.setDisable(false);
                                    }
                                } else {
                                    orderMenuItems.remove(new OrderItems(menuItem, 0));
                                    if(orderMenuItems.isEmpty()) {
                                        placeOrderButton.setDisable(true);
                                    }
                                }
                            });

                            quantityField.textProperty().addListener((listener, oldValue, newValue) -> {
                                if(quantityField.getText().isEmpty()) {
                                    checkBox.disableProperty().setValue(true);
                                    checkBox.selectedProperty().setValue(false);
                                    orderMenuItems.remove(new OrderItems(menuItem, 0));
                                    return;
                                } else {
                                    checkBox.disableProperty().setValue(false);
                                }
                                if(checkBox.isSelected()) {
                                    try{
                                        orderMenuItems.remove(new OrderItems(menuItem, 0));
                                        orderMenuItems.add(new OrderItems(menuItem, Integer.parseInt(quantityField.getText())));
                                    } catch (NumberFormatException numberFormatException) {
                                        MessageAlert.showErrorMessage((Stage)(checkBox.getScene().getWindow()), "Eroare valoare cantitate");
                                    }
                                }
                            });

                            quantityField.setStyle("-fx-pref-width: 50");
                            itemBox.getChildren().setAll(checkBox, pane1, nameLabel, pane2, priceLabel, pane3, quantityField);
                            HBox.setHgrow(pane1, Priority.ALWAYS);
                            HBox.setHgrow(pane2, Priority.ALWAYS);
                            HBox.setHgrow(pane3, Priority.ALWAYS);
                            nameLabel.setText(menuItem.getItem());
                            priceLabel.setText(menuItem.getPrice().toString() + " " + menuItem.getCurrency());

                            setGraphic(itemBox);
                        }
                    }
                };
            });
            menuItemListView.setItems(itemsObservableList);

        }

        @Override
        protected void updateItem(CategoryItems categoryItems, boolean empty) {
            super.updateItem(categoryItems, empty);
            setText(null);
            if(empty) {
                setGraphic(null);
            } else {
                categoryLabel.setText(categoryItems.getId());
                itemsObservableList.setAll(categoryItems.getItems());

                setGraphic(categoryBox);
            }
        }
    }


    @Override
    public void update(OrderEvent event) {
        if(Objects.equals(event.getOrder().getTableId(), table.getId())) {
            mainPane.getChildren().setAll();
            mainPane.setCenter(orderStatusLabel);
            if(event.getOrder().getOrderStatus().equals(OrderStatus.PLACED)) {
                orderStatusLabel.setText("Your order has been placed!");
            } else if(event.getOrder().getOrderStatus().equals(OrderStatus.PREPARING)) {
                orderStatusLabel.setText("Your order is being prepared!");
            } else {
                orderStatusLabel.setText("Your order has been delivered!");
                mainPane.setCenter(orderStatusLabel);
            }
        }
    }

    private static class CategoryItems extends Entity<String> {
        private List<MenuItem> menuItems;

        public CategoryItems(String category, List<MenuItem> menuItems) {
            super(category);
            this.menuItems = menuItems;
        }

        private List<MenuItem> getItems() {
            return menuItems;
        }
    }

    private void getMenuData() {
        List<MenuItem> menuItems = (List)service.getAllMenuItems();
        Collection<String> categories = (Collection<String>) menuItems.stream().map(menuItem -> menuItem.getCategory()).collect(Collectors.toSet());

        categories.forEach(category -> {
            List<MenuItem> menuItemsCategory = menuItems.stream().filter(menuItem -> menuItem.getCategory().equals(category)).toList();
            menuObservableList.add(new CategoryItems(category, menuItemsCategory));
        });
    }

    private void initMenu() {
        menuListView.setCellFactory(list -> new CategoryItemsCell(orderMenuItems, placeOrderButton));
        getMenuData();
        menuListView.setItems(menuObservableList);
    }

    private void updateMenu() {
        getMenuData();
        menuListView.setItems(menuObservableList);
    }

    public void placeOrder(ActionEvent actionEvent) {
        service.placeOrder(table.getId(), orderMenuItems);
    }
}
