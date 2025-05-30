package com.example.restaurant.repositorty.database;

import com.example.restaurant.domain.MenuItem;
import com.example.restaurant.domain.Table;
import com.example.restaurant.exceptions.InexistentEntityException;
import com.example.restaurant.repositorty.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablesDbRepository implements Repository<Integer, Table> {
    private String url;
    private String username;
    private String password;

    public TablesDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Table getTableFromResultSet(ResultSet resultSet) throws SQLException, SQLException {
        Integer id = resultSet.getInt("id");
        return new Table(id);
    }

    private boolean Exists(Integer id){
        String sql = "SELECT * FROM tables WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(this.url,this.username,this.password);
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return  true;
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public Table find(Integer id) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM tables WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getTableFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Table> getAll() {
        List<Table> tables = new ArrayList<>();
        String sql = "SELECT id FROM tables";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Table table = getTableFromResultSet(resultSet);
                tables.add(table);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return tables;
    }

    @Override
    public Map<Integer, Table> getMap() {
        Map<Integer, Table> tables = new HashMap<>();
        String sql = "SELECT id FROM tables";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Table table = getTableFromResultSet(resultSet);
                tables.put(table.getId(), table);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return tables;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM tables";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
