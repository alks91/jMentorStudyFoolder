package service;

import DAO.UserDao;
import DAO.UserDaoFactory;
import model.User;


import java.util.List;

public class UserService implements UserDao {

    private static UserService userService;
    private static UserDao userDao;

    private UserService() {

    }

    public static UserService getInstance() {
        if(userService == null || userDao == null) {
            userService = new UserService();
            userDao = UserDaoFactory.getInstance().getUserDao();
        }
        return userService;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }
    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
    @Override
    public void updateNameById(Long id, String name) {
        userDao.updateNameById(id, name);
    }

}
