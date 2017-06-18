package UsersService.Repository;

 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import UsersService.Models.UserPicture;



@Component
public interface UserPictureRepository  extends CrudRepository<UserPicture, Integer> {
    Page<UserPicture> findAll(Pageable pageable);
    //AccountModel findOne(ID id);
}