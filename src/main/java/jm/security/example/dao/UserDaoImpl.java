package jm.security.example.dao;


import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        List<User> allUsers = entityManager.createQuery("select u from User u", User.class).getResultList();
        System.out.println("Запрос прошел");
        return allUsers;
    }

    @Override
    public void addUser(User user) {
        System.out.println("Добавляем юзера " + user.toString());

        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        User us = entityManager.find(User.class, id);
        entityManager.remove(us);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        System.out.println("Получаем юзера");
        return entityManager.createQuery("SELECT u from User u where u.username =:name", User.class)
                .setParameter("name", username).getSingleResult();
    }
}

