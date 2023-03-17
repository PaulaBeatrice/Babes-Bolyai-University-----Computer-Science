package com.example.restaurant.service;

import com.example.restaurant.domain.*;
import com.example.restaurant.repositorty.Repository;
import com.example.restaurant.repositorty.database.OrdersDbRepository;
import com.example.restaurant.utils.observer.Observer;
import com.example.restaurant.utils.utils.OrderEvent;

import java.time.LocalDateTime;
import java.util.List;

public class Service {
    private Repository <Integer, Table> tableRepository;
    private OrdersDbRepository ordersDbRepository;
    private Repository<Integer, MenuItem> menuItemRepository;

    public Service(Repository<Integer, Table> tableRepository, Repository<Integer, Orders>  ordersDbRepository, Repository<Integer, MenuItem> menuItemRepository) {
        this.tableRepository = tableRepository;
        this.ordersDbRepository = (OrdersDbRepository) ordersDbRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Iterable<Table> getAllTables(){
        return tableRepository.getAll();
    }

    public Iterable<Orders> getAllOrders(){
        return ordersDbRepository.getAll();
    }

    public Iterable<MenuItem> getAllMenuItems(){
        return menuItemRepository.getAll();
    }

    public void addOrderObservers(Observer<OrderEvent> observer){
        ordersDbRepository.addObserver(observer);
    }

    public void placeOrder(int table_id, List<OrderItems> orderItems){
        Orders order = new Orders(0, new Table(table_id), LocalDateTime.now(), orderItems, OrderStatus.PLACED);
        ordersDbRepository.add(order);
    }

    public void prepareOrder(int order_id, int table_id) {
        Orders order = new Orders(order_id, new Table(table_id), LocalDateTime.now(), null, OrderStatus.PREPARING);
        ordersDbRepository.update(order);
    }

    public void deliverOrder(int order_id, int table_id) {
        Orders order = new Orders(order_id, new Table(table_id), LocalDateTime.now(), null, OrderStatus.SERVED);
        ordersDbRepository.update(order);
    }
}
