package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE if not exists users " +
                "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR (50), " +
                "age TINYINT not NULL)";
        try (Connection connection = new Util().getConnection()) {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("Error to creat table " + e.getMessage());
        }
    }

    public void dropUsersTable() {

        try (Connection connection = new Util().getConnection()) {
            connection.createStatement().execute("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Error to drop " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try(Connection connection = new Util().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем — "+ name +" добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println("Error to add user " + e.getMessage());
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = new Util().getConnection()) {
            connection.createStatement().execute("DELETE FROM users WHERE id=" + id + ";");
        } catch (SQLException e) {
            System.out.println("Error to delete user " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = new Util().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("ID"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                usersList.add(user);
            }
            return usersList;
        } catch (SQLException e) {
            System.out.println("Error to get all users " + e.getMessage());
        }
        return null;
    }


    public void cleanUsersTable() {
        try (Connection connection = new Util().getConnection()) {
            connection.createStatement().execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("Error to truncate table users " + e.getMessage());
        }
    }
}