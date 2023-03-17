package repository;

import domain.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDBRepo implements  Repository<Long, User>{
    private String URL;
    private String username;
    private String password;

    public UserDBRepo(String URL, String username, String password) {
        this.URL = URL;
        this.username = username;
        this.password = password;
    }

    @Override
    public User findOne(Long aLong) {
        Set<User> usersSet = new HashSet<>();
        try(Connection connection= DriverManager.getConnection(URL, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");

            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                Long ID = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                User user = new User(firstName, lastName);
                user.setId(ID);
                usersSet.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usersSet;
    }

    @Override
    public Iterable<User> findall() {
        Set<User> usersSet = new HashSet<>();
        try(Connection connection= DriverManager.getConnection(URL, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery()){
            while(resultSet.next()){
                Long ID = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                User user = new User(firstName, lastName);
                user.setId(ID);
                usersSet.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usersSet;
    }

    @Override
    public User save(User entity) {
        String sql = "INSERT INTO users(first_name, last_name) VALUES(?,?)";
        try(Connection connection= DriverManager.getConnection(URL, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(Long aLong) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
