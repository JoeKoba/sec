package com.example.sec.repository;

import com.example.sec.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User find(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> itemRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("email"), email));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select  u from User  u", User.class).getResultList();
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public void save(User entity) {
        if (!entity.persisted()) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void delete(User entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}