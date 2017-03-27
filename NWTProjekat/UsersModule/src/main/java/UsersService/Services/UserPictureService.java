package UsersService.Services;

import UsersService.Models.UserPicture;

public interface UserPictureService 
{
    Iterable<UserPicture> listAllUserPictures();

    UserPicture getUserPictureById(Integer id);

    UserPicture saveUserPicture(UserPicture userPicture);

    void deleteUserPicture(Integer id);
}