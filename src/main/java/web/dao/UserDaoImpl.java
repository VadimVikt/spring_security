package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserByUsername(String name) {
        System.out.println("Получаем юзера");

        User u = (User) entityManager.createQuery("SELECT  u from User u where u.username = :name", User.class)
                .setParameter("name", name).getSingleResult();

        System.out.println(u.toString());
        return u;
    }

    @Override
    public void addUser(User user) {
        System.out.println("Добавляем");
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        System.out.println("Пробуем удалить юзера с ид - " + id);
        User us = entityManager.find(User.class, id);
        System.out.println(us.toString());
        entityManager.remove(us);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        System.out.println("Запрос");
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
