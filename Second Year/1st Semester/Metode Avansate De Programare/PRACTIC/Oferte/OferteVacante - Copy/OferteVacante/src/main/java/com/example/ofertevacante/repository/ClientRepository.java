package com.example.ofertevacante.repository;

import com.example.ofertevacante.domain.Client;
import com.example.ofertevacante.domain.Hobbies;
import com.example.ofertevacante.domain.Hotel;
import com.example.ofertevacante.domain.typeHotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientRepository implements Repository<Long, Client>{
    private String url;
    private String username;
    private String password;

    public ClientRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Integer fidelity = resultSet.getInt("fidelity_grade");
        Integer varsta = resultSet.getInt("varsta");
        Hobbies hobby = Hobbies.valueOf(resultSet.getString("hobby"));
        return new Client(id,name,fidelity,varsta,hobby);
    }


    @Override
    public Client find(Long aLong) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Client save(Client entity) {
        return null;
    }

    @Override
    public Iterable<Client> getAll() {
        List<Client> clienti = new ArrayList<>();
        String sql = "SELECT * FROM clienti";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                clienti.add(client);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return clienti;
    }

    @Override
    public Map<Long, Client> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }
}
