package com.example.ofertevacante.repository;

import com.example.ofertevacante.domain.Hotel;
import com.example.ofertevacante.domain.Location;
import com.example.ofertevacante.domain.SpecialOffer;
import com.example.ofertevacante.domain.typeHotel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OferteRepository implements Repository<Double, SpecialOffer> {
    private String url;
    private String username;
    private String password;

    public OferteRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public SpecialOffer find(Double aDouble) throws IllegalArgumentException {
        return null;
    }

    @Override
    public SpecialOffer save(SpecialOffer entity) {
        return null;
    }

    @Override
    public Iterable<SpecialOffer> getAll() {
        List<SpecialOffer> oferte = new ArrayList<>();
        String sql = "SELECT * FROM oferte";

        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                SpecialOffer oferta = getOfertaFromResult(resultSet);
                oferte.add(oferta);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return oferte;
    }

    private SpecialOffer getOfertaFromResult(ResultSet resultSet) throws SQLException {
        Double id = resultSet.getDouble("id");
        Double idH = resultSet.getDouble("id_hotel");
        LocalDateTime data = resultSet.getTimestamp("start_date").toLocalDateTime();
        LocalDateTime data1 = resultSet.getTimestamp("end_date").toLocalDateTime();
        Integer p = resultSet.getInt("percent");
        return new SpecialOffer(id, idH,data,data1,p);
    }

    @Override
    public Map<Double, SpecialOffer> getMap() {
        return null;
    }

    @Override
    public void clear() {

    }
}
