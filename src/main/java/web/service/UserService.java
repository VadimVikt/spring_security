package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDaoImpl userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByUsername(s);
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRoleById(2L));
        user.setRoles(roles);
        userDao.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

}
