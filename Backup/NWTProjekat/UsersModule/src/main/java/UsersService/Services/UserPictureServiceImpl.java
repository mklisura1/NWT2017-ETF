package UsersService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UsersService.Models.UserPicture;
import UsersService.Repository.UserPictureRepository;

@Service
public class UserPictureServiceImpl implements UserPictureService 
{
	@Autowired
	private UserPictureRepository userPictureRepository;

    @Override
    public Iterable<UserPicture> listAllUserPictures() 
    {
        return userPictureRepository.findAll();
    }

    @Override
    public UserPicture getUserPictureById(Integer id) 
    {
        return userPictureRepository.findOne(id);
    }

    public UserPicture saveUserPicture(UserPicture userPicture) 
    {
        return userPictureRepository.save(userPicture);
    }

    @Override
    public void deleteUserPicture(Integer id) 
    {
    	userPictureRepository.delete(id);
    }
}