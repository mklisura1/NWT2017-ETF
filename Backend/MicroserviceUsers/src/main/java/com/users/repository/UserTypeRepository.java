package com.users.repository;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.users.model.User;
import com.users.model.UserType;

public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
    Page<UserType> findAll(Pageable pageable);
    //AccountModel findOne(ID id);
}
