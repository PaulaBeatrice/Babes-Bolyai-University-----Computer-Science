package com.example.examen.repository;

import com.example.examen.domain.Location;
import com.example.examen.domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorySpecialOffer {
    List<SpecialOffer> entities;
    private String username;
    private String password;
    private String url;

    public RepositorySpecialOffer(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.entities=new ArrayList<>();
        loadData();
    }

    public void loadData() {
        this.entities.clear();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from offers");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Double specialOfferId=resultSet.getDouble("id");
                Double hotelId=resultSet.getDouble("hotel");
                LocalDate startDate= resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate=resultSet.getDate("end_date").toLocalDate();
                Integer percents=resultSet.getInt("percents");
                //System.out.println(startDate);
                SpecialOffer specialOffer=new SpecialOffer(specialOfferId,hotelId,startDate,endDate,percents);
                this.entities.add(specialOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SpecialOffer> getEntities(){
        loadData();
        return this.entities;
    }
}
