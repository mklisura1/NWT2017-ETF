package UsersService.Services;

import UsersService.Models.UserType;

public interface UserTypeService 
{
    Iterable<UserType> listAllUserTypes();
    
    boolean isUserTypeExist(UserType userType);

    UserType getUserTypeById(Integer id);

    UserType saveUserType(UserType userType);
    
    void updateUserType(UserType userType);

    void deleteUserType(Integer id);
}