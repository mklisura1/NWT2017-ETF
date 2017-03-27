package UsersService.Repository;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import UsersService.Models.UserType;


public interface UserTypeRepository extends CrudRepository<UserType, Integer> {
    Page<UserType> findAll(Pageable pageable);
    //AccountModel findOne(ID id);
}
