package jm.security.example.service;

import jm.security.example.dao.RoleDao;
import jm.security.example.dao.UserDao;
import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    @Transactional
    public List<User> allUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public void add(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add((Role) roleDao.getRoleById(2L));
        System.out.println(roleDao.getRoleById(2L));
        user.setRoles(roles);
        userDao.addUser(user);

    }

    @Override
    @Transactional()
    public void delete(long id) {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional()
    public void edit(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
