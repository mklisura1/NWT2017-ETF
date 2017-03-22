package com.users.repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.users.model.User;

@Component
public interface UserRepository extends CrudRepository<User, Integer>{
}