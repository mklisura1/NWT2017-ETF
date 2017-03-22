package com.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.model.User;
import com.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;

    @Override
    public Iterable<User> listAllUsers() 
    {
        return userRepository.findAll();
    }
    
    public boolean isUserExist(User user) 
    {
    	return userRepository.exists(user.getUser_id());
    }

    @Override
    public User getUserById(Integer id) 
    {
        return userRepository.findOne(id);
    }

    public User saveUser(User User) 
    {
        return userRepository.save(User);
    }
    
    public void updateUser(User user)
    {     
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) 
    {
        userRepository.delete(id);
    }
}