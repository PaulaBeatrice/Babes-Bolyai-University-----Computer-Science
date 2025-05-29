package com.example.anar.repository;

import com.example.anar.domain.Localitate;
import com.example.anar.domain.Rau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocalitateRepo implements Repository<Integer, Localitate>{
    private String url;
    private String username;
    private String password;

    public LocalitateRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Localitate getLocFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        Integer rau = resultSet.getInt("rau");
        Integer cota_min = resultSet.getInt("cota_min");
        Integer cota_max = resultSet.getInt("cota_max");
        return new Localitate(id,nume,rau,cota_min,cota_max);
    }

    @Override
    public Localitate find(Integer integer) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Localitate> getAll() {
        List<Localitate> localitates = new ArrayList<>();
        String sql = "SELECT * FROM localitati";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Localitate localitate = getLocFromResultSet(resultSet);
                localitates.add(localitate);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return localitates;
    }

    @Override
    public Map<Integer, Localitate> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Localitate save(Localitate c) {
        return null;
    }

    @Override
    public void update(Integer idRau, Integer newCota) {

    }
}
