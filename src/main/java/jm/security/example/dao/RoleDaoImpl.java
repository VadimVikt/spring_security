package jm.security.example.dao;


import jm.security.example.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl  implements RoleDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public RoleDao getRoleById(long id) {
        return (RoleDao) entityManager.find(Role.class, id);
    }
}
