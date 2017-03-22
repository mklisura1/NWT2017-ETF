package com.users.repository;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.users.model.User;
import com.users.model.UserPicture;
import com.users.model.UserType;

@Component
public interface UserPictureRepository  extends CrudRepository<UserPicture, Integer> {
    Page<UserPicture> findAll(Pageable pageable);
    //AccountModel findOne(ID id);
}