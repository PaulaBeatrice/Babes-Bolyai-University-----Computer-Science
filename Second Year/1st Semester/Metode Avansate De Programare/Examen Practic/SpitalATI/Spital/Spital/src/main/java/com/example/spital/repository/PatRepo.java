package com.example.spital.repository;

import com.example.spital.domain.Pat;
import com.example.spital.domain.TipPat;
import com.example.spital.domain.YesNo;
import com.example.spital.exceptions.InexistentEntityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatRepo implements Repository<Integer,Pat>{
    private String url;
    private String username;
    private String password;

    public PatRepo(String url, String username, String password) {
        this.url = "jdbc:postgresql://localhost:5432/spital";
        this.username = "postgres";
        this.password = "postgress";
    }

    private Pat getPatFromResultSet(ResultSet resultSet)throws SQLException {
        Integer id = resultSet.getInt("id");
        TipPat tip = TipPat.valueOf(resultSet.getString("tip")) ;
        YesNo ventilatie = YesNo.valueOf(resultSet.getString("ventilatie"));
        String pacient = resultSet.getString("pacient");
        return new Pat(id,tip,ventilatie,pacient);
    }

    private boolean Exists(Integer id){
        String sql = "SELECT * FROM paturi WHERE id = ?";
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
    public Pat find(Integer id) throws IllegalArgumentException {
        if(username == null) {
            throw new IllegalArgumentException("Id must be not null!");
        }

        String sql = "SELECT * FROM paturi WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) {
                throw new InexistentEntityException(id + " does not exist!");
            } else {
                return getPatFromResultSet(resultSet);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Iterable<Pat> getAll() {
        List<Pat> paturi = new ArrayList<>();
        String sql = "SELECT * FROM paturi";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Pat pat = getPatFromResultSet(resultSet);
                paturi.add(pat);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return paturi;
    }

    @Override
    public Map<Integer, Pat> getMap() {
        return null;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM paturi";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Pat save(Pat c) {
        return null;
    }

    @Override
    public void update(String val, Integer id) {
        String sql = "UPDATE paturi SET pacient = ? WHERE id = ?";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, val);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
