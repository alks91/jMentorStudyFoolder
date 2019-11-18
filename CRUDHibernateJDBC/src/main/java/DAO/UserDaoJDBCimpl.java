package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDao {

   private static UserDaoJDBCimpl userDaoJDBCimpl;
   private Connection connection;

    private UserDaoJDBCimpl() {

    }

    public UserDaoJDBCimpl getInstance(Connection connection) {
        if(userDaoJDBCimpl == null) {
            userDaoJDBCimpl = new UserDaoJDBCimpl(connection);
        }
        return userDaoJDBCimpl;
    }

    public UserDaoJDBCimpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<User> getAllUser() {
        List result = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users_info");
            while (resultSet.next()) {
                result.add(
                        new User(Long.parseLong(resultSet.getString("id")),
                                resultSet.getString("name"),
                                resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean addUser(User user) {
        boolean result = false;
        try {
            Statement stat = connection.createStatement();
            ResultSet resultSet = stat.executeQuery("select * from users_info where name='" + user.getName() + "'");
            if (!resultSet.next()) {
                String sql = "insert into users_info(name, email) values (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.executeUpdate();
                statement.close();
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void deleteUserById(Long id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from users_info where id=" + id);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateNameById(Long id, String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("update users_info set name = ? where id = ?");
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getUserById(Long id) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users_info where id=" + id);
            resultSet.next();
            user = new User(Long.valueOf(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}