package com.example.restaurant.controller;

import com.example.restaurant.domain.OrderStatus;
import com.example.restaurant.domain.Orders;
import com.example.restaurant.service.Service;
import com.example.restaurant.utils.observer.Observer;
import com.example.restaurant.utils.utils.OrderEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.List;

public class StaffController implements Observer<OrderEvent> {
    Service service;

    @FXML
    TableView<Orders> placedOrdersTableView;
    ObservableList<Orders> placedOrdersObservableList;
    @FXML
    TableView<Orders> preparingOrdersTableView;
    ObservableList<Orders> preparingOrdersObservableList;

    @FXML
    TableColumn prepareColumn;
    @FXML
    TableColumn deliverColumn;

    @FXML
    public void setService(Service service) {
        this.service = service;
        this.service.addOrderObservers(this);

        initPlacedOrdersTableView();
        updatePlacedOrdersTableView();
        initPreparingOrdersTableView();
        updatePreparingdOrdersTableView();
    }

    private void initPlacedOrdersTableView() {
        Callback<TableColumn<Orders, String>, TableCell<Orders, String>> cellFactory =
                new Callback<TableColumn<Orders, String>, TableCell<Orders, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Orders, String> param) {
                        final TableCell<Orders, String> cell = new TableCell<Orders, String>() {

                            final Button btn = new Button("Prepare");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Orders order = getTableView().getItems().get(getIndex());
                                        service.prepareOrder(order.getId(), order.getTableId());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        prepareColumn.setCellFactory(cellFactory);
    }

    private void initPreparingOrdersTableView() {
        Callback<TableColumn<Orders, String>, TableCell<Orders, String>> cellFactory =
                new Callback<TableColumn<Orders, String>, TableCell<Orders, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Orders, String> param) {
                        final TableCell<Orders, String> cell = new TableCell<Orders, String>() {

                            final Button btn = new Button("Deliver");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Orders order = getTableView().getItems().get(getIndex());
                                        service.deliverOrder(order.getId(), order.getTableId());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        deliverColumn.setCellFactory(cellFactory);
    }

    private void updatePlacedOrdersTableView() {
        List<Orders> placedOrders = ((List<Orders>)this.service.getAllOrders()).stream().filter(order -> order.getOrderStatus().equals(OrderStatus.PLACED)).toList();
        placedOrdersObservableList = FXCollections.observableArrayList(placedOrders);
        placedOrdersTableView.setItems(placedOrdersObservableList);
    }

    private void updatePreparingdOrdersTableView() {
        List<Orders> preparingOrders = ((List<Orders>)this.service.getAllOrders()).stream().filter(order -> order.getOrderStatus().equals(OrderStatus.PREPARING)).toList();
        preparingOrdersObservableList = FXCollections.observableArrayList(preparingOrders);
        preparingOrdersTableView.setItems(preparingOrdersObservableList);
    }

    @Override
    public void update(OrderEvent event) {
        updatePlacedOrdersTableView();
        updatePreparingdOrdersTableView();
    }
}