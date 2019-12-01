package com.javacode.service;

import com.javacode.model.User;

import java.util.List;

public interface UserService {

    public List<User> allUsersList();
    public User getUserById(int id);
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUserById(int id);
}
