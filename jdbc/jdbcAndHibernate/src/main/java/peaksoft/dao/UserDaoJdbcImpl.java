package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.jdbcUtilConnection()) {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS user_1 (" +
                    "id SERIAL," +
                    "name TEXT," +
                    "lastName TEXT," +
                    "age SMALLINT);";
            statement.executeUpdate(SQL);
            System.out.println("Таблица успешно создано.");
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String drop_SQL = "DROP TABLE IF EXISTS user_1;";
        try (Connection connection = Util.jdbcUtilConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(drop_SQL);
            preparedStatement.executeUpdate();
            System.out.println("Таблица успешно удалено.");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save_SQL = "INSERT INTO user_1 (name, lastName, age) VALUES (?, ?, ?);";
        try (Connection connection = Util.jdbcUtilConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(save_SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println(name + " - успешно добавлен в таблицу.");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String remove_SQL = "DELETE FROM user_1 WHERE id = ?;";
        try (Connection connection = Util.jdbcUtilConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(remove_SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("id под номером: " + id + " - успешно удален из таблицы.");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String get_All_SQL = "SELECT * FROM user_1;";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.jdbcUtilConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get_All_SQL);
            System.out.println("Таблица вызвана с помощью ArrayList: \n");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean_SQL = "DELETE FROM user_1";
        try (Connection connection = Util.jdbcUtilConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(clean_SQL);
            preparedStatement.executeUpdate();
            System.out.println("Таблица полностью успешно очищена.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}