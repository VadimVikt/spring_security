package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String name);
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(long id);
    User getUserById(long id);
    List<User> listUsers();
}
