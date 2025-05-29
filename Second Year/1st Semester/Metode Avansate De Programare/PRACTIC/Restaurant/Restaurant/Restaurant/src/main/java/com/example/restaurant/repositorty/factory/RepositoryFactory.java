package com.example.restaurant.repositorty.factory;

import com.example.restaurant.domain.MenuItem;
import com.example.restaurant.domain.Orders;
import com.example.restaurant.domain.Table;
import com.example.restaurant.repositorty.Repository;
import com.example.restaurant.repositorty.database.MenuItemsDbRepository;
import com.example.restaurant.repositorty.database.OrdersDbRepository;
import com.example.restaurant.repositorty.database.TablesDbRepository;

public class RepositoryFactory {
    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Integer, Table> createTablesRepository() {
        return new TablesDbRepository("jdbc:postgresql://localhost:5432/restaurant",
                "postgres", "postgress");
    }

    private static Repository<Integer, Orders> createOrdersRepository() {
        return new OrdersDbRepository("jdbc:postgresql://localhost:5432/restaurant",
                "postgres", "postgress");
    }

    private static Repository<Integer, MenuItem> createMenuItemsRepository() {
        return new MenuItemsDbRepository("jdbc:postgresql://localhost:5432/restaurant",
                "postgres", "postgress");
    }

    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case TABLES:
                return createTablesRepository();
            case ORDERS:
                return createOrdersRepository();
            default:
                return createMenuItemsRepository();
        }
    }

    /**
     * Method that retuns the instance of the repository factory
     * @return the repository factory
     */
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
