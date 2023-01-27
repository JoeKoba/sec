package com.example.sec.repository;



import com.example.sec.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleRepository {
    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;
}
