package com.example.sec.repository;



import com.example.sec.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> find(Long id);

    User find(String email);

    void save(User entity);

    void delete(User entity);

    void addUser(User user);
}
