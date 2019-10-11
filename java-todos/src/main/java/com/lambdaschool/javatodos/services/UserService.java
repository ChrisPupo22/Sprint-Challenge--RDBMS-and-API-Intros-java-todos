package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.User;


import java.util.List;

public interface UserService
{

    List<User> findAll();

    List<User> findByNameContaining(String username);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    void deleteUserRole(long userid,
                        long roleid);

    User update(User user,
                long id,
                boolean isAdmin);

    void addUserRole(long userid,
                     long roleid);
}
