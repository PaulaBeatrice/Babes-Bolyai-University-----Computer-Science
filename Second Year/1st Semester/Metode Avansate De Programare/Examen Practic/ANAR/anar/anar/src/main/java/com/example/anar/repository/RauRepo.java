package com.example.anar.repository;

import com.example.anar.domain.Rau;
import com.example.anar.exceptions.InexistentEntityException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RauRepo implements Repository<Integer, Rau>{
    private String url;
    private String username;
    private String password;

    public RauRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Rau getRauFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        Integer cota = resultSet.getInt("cota_medie");
        return new Rau(id,nume,cota);
    }

    @Override
    public Rau find(Integer id) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM rauri WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getRauFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }


    @Override
    public Iterable<Rau> getAll() {
        List<Rau> rauri = new ArrayList<>();
        String sql = "SELECT * FROM rauri";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Rau rau = getRauFromResultSet(resultSet);
                rauri.add(rau);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rauri;
    }

    @Override
    public Map<Integer, Rau> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Rau save(Rau c) {
        return null;
    }

    @Override
    public void update(Integer idRau, Integer newCota) {
        String sql = "UPDATE rauri SET cota_medie = ? WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newCota);
            statement.setInt(2, idRau);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
