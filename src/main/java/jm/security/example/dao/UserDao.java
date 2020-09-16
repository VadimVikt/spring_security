package jm.security.example.dao;

import jm.security.example.model.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(long id);
    User getUserById(long id);
    User getUserByName(String name);
}
