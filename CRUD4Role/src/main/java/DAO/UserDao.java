package DAO;

import model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUser();
    public boolean addUser(User user);
    public void deleteUserById(Long id);
    public User getUserById(Long id);
    public void updateEmailById(Long id, String email);
    public boolean isExistUser(String email);
    public boolean isAuthUser(String email, String password) ;
    public User getUserByEmail(String email);
}
