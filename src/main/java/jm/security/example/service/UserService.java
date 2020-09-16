package jm.security.example.service;

import jm.security.example.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    User getUserByName(String name);
}
