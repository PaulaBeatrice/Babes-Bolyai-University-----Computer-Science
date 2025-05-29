package com.example.model1.repos;

import com.example.model1.domains.Client;
import com.example.model1.domains.Entity;
import com.example.model1.domains.Hobbies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ClientRepo<ID, E extends Entity<ID>> implements Repository<Long, Client> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Client> findOne(Long aLong, Date startDate, Date endDate) {
        return null;
    }

    public Client findEntity(Long id) {
        String query = "SELECT * from client";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long clientId = resultSet.getLong("clientId");
                String name = resultSet.getString("name");
                Integer fidelityGrade = resultSet.getInt("fidelityGrade");
                Integer age = resultSet.getInt("age");
                String hobbies = resultSet.getString("hobbies");
                if (Objects.equals(clientId, id)) {
                    Client client = new Client(clientId, name, fidelityGrade, age, Hobbies.valueOf(hobbies));
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double findOneByName(String name) {
        return null;
    }

    @Override
    public Iterable<Client> findAllWithName(String name) {
        return null;
    }

    @Override
    public Client add(Client entity) {
        return null;
    }

    @Override
    public List<String> getAll() {
        return null;
    }

    @Override
    public List<Client> getAllEntitys() {
        return null;
    }

    @Override
    public List<Client> offers(Date date, Integer fidelityGrade) {

        return null;
    }
}
