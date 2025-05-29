package com.example.login2.repository;

import com.example.login2.domain.Nevoie;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NevoiRepository implements Repository<Integer, Nevoie> {
    private String url;
    private String username;
    private String password;

    public NevoiRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Nevoie find(Integer aDouble) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Nevoie save(Nevoie entity)
    {
        String sql = "INSERT INTO nevoi(id, titlu,descriere,deadline, om_in_nevoie,om_salvator , status) VALUES (?, ?, ?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getTitlu());
            statement.setString(3, entity.getDescriere());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getDeadline()));
            statement.setInt(5, entity.getOminNevoie());
            statement.setInt(6, entity.getOnSalvator());
            statement.setString(7, entity.getStatus());
            //System.out.printf(statement.toString());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return entity;
    }

    @Override
    public Iterable<Nevoie> getAll() {
        List<Nevoie> nevoi = new ArrayList<>();
        String sql = "SELECT * FROM nevoi";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Nevoie nevoie = getNevoiFromResult(resultSet);
                nevoi.add(nevoie);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return nevoi;
    }

    private Nevoie getNevoiFromResult(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String titlu = resultSet.getString("titlu");
        String descriere = resultSet.getString("descriere");
        LocalDateTime deadline = resultSet.getTimestamp("deadline").toLocalDateTime();
        Integer om_in_nevoie = resultSet.getInt("om_in_nevoie");
        Integer om_salvator= resultSet.getInt("om_salvator");
        String status = resultSet.getString("status");
        return new Nevoie(id,titlu,descriere ,deadline,om_in_nevoie,om_salvator,status);
    }


    @Override
    public Map<Integer, Nevoie> getMap() {
        return null;
    }
    @Override
    public void clear() {

    }

    @Override
    public Nevoie update(Nevoie entity) {
        String sql = "UPDATE nevoi SET om_salvator = ?, status = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entity.getOnSalvator());
            statement.setInt(3, entity.getId());
            statement.setString(2, "erou salvator");
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

}
