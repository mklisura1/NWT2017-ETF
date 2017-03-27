package UsersService.Repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import UsersService.Models.User;


@Component
public interface UserRepository extends CrudRepository<User, Integer>{
	
}