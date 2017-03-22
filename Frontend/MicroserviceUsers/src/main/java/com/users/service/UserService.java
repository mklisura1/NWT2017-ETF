package com.users.service;

import com.users.model.User;

public interface UserService 
{
    Iterable<User> listAllUsers();
    
    boolean isUserExist(User user);

    User getUserById(Integer id);

    User saveUser(User user);
    
    void updateUser(User user);

    void deleteUser(Integer id);
}