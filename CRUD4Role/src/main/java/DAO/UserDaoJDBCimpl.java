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

    public static UserDaoJDBCimpl getInstance(Connection connection) {
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
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                User.Role.valueOf(resultSet.getString("role"))));
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
            ResultSet resultSet = stat.executeQuery("select * from users_info where name='" + user.getEmail() + "'");
            if (!resultSet.next()) {
                String sql = "insert into users_info(email, password) values (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());
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
    public void updateEmailById(Long id, String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("update users_info set email = ? where id = ?");
            statement.setString(1, email);
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
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    User.Role.valueOf(resultSet.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users_info where email = + ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    User.Role.valueOf(resultSet.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isExistUser(String email) {
        User user;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users_info where email =  + ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet == null) {
                return false;
            } else {
                user = getUserByEmail(email);
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isAuthUser(String email, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users_info where email =  + ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.getString("password").equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}