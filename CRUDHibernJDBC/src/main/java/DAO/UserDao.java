package DAO;

import model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUser();
    public boolean addUser(User user);
    public void deleteUserById(Long id);
    public User getUserById(Long id);
    public void updateNameById(Long id, String name);
}
