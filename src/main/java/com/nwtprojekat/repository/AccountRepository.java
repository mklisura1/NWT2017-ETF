package com.nwtprojekat.repository;

import com.nwtprojekat.models.AccountModel;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;

/**
 * Created by Hare on 17.03.2017..
 */
public interface AccountRepository extends CrudRepository<AccountModel, Integer> {
    Page<AccountModel> findAll(Pageable pageable);
    //AccountModel findOne(ID id);
}
