package UsersService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UsersService.Models.UserType;
import UsersService.Repository.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService 
{
	@Autowired
	private UserTypeRepository userTypeRepository;

    @Override
    public Iterable<UserType> listAllUserTypes() 
    {
        return userTypeRepository.findAll();
    }
    
    public boolean isUserTypeExist(UserType userType) 
    {
    	return userTypeRepository.exists(userType.getUser_type_id());
    }

    @Override
    public UserType getUserTypeById(Integer id) 
    {
        return userTypeRepository.findOne(id);
    }

    public UserType saveUserType(UserType userType) 
    {
        return userTypeRepository.save(userType);
    }
    
    public void updateUserType(UserType userType)
    {     
        userTypeRepository.save(userType);
    }

    @Override
    public void deleteUserType(Integer id) 
    {
        userTypeRepository.delete(id);
    }
}