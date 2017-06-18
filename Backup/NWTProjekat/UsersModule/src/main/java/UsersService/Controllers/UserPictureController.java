package UsersService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import UsersService.Models.UserPicture;
import UsersService.Services.UserPictureService;

@Controller
@RequestMapping(value="/api")
public class UserPictureController 
{
	@Autowired
	private UserPictureService userPictureService;
	
	//-------------------Retrieve All UserPictures--------------------------------------------------------
    
    @RequestMapping(value = "/user/picture", method = RequestMethod.GET)
    public ResponseEntity<List<UserPicture>> listAllUsers() {
        List<UserPicture> userPictures = (List<UserPicture>) userPictureService.listAllUserPictures();
        if(userPictures.isEmpty()){
            return new ResponseEntity<List<UserPicture>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserPicture>>(userPictures, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single UserPicture--------------------------------------------------------
     
    @RequestMapping(value = "/user/picture/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPicture> getUserPicture(@PathVariable("id") Integer id) {
        System.out.println("Fetching UserPicture with id " + id);
        UserPicture userPicture = userPictureService.getUserPictureById(id);
        if (userPicture == null) {
            System.out.println("UserPicture with id " + id + " not found");
            return new ResponseEntity<UserPicture>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserPicture>(userPicture, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a UserPicture--------------------------------------------------------
    /* 
    @RequestMapping(value = "/user/picture/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserPicture(@RequestBody UserPicture user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating UserPicture " + user.getFirst_name());
 
        if (userPictureService.isUserPictureExist(user)) {
            System.out.println("A UserPicture with name " + user.getFirst_name() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUserPicture(user);;
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserPicture_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 	
     
    //------------------- Update a UserPicture --------------------------------------------------------
     
    @RequestMapping(value = "/user/picture/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserPicture> updateUserPicture(@PathVariable("id") Integer id, @RequestBody UserPicture user) {
        System.out.println("Updating UserPicture " + id);
         
        UserPicture currentUserPicture = userPictureService.getUserPictureById(id);
         
        if (currentUserPicture==null) {
            System.out.println("UserPicture with id " + id + " not found");
            return new ResponseEntity<UserPicture>(HttpStatus.NOT_FOUND);
        }
 
        currentUserPicture.setFirst_name(user.getFirst_name());
        currentUserPicture.setLast_name(user.getLast_name());
         
        userPictureService.updateUserPicture(currentUserPicture);;
        return new ResponseEntity<UserPicture>(currentUserPicture, HttpStatus.OK);
    }
    */
    
    //------------------- Delete a UserPicture --------------------------------------------------------
     
    @RequestMapping(value = "/user/picture/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserPicture> deleteUserPicture(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting UserPicture with id " + id);
 
        UserPicture user = userPictureService.getUserPictureById(id);
        if (user == null) {
            System.out.println("Unable to delete. UserPicture with id " + id + " not found");
            return new ResponseEntity<UserPicture>(HttpStatus.NOT_FOUND);
        }

        userPictureService.deleteUserPicture(id);
        return new ResponseEntity<UserPicture>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All UserPictures --------------------------------------------------------
    
    @RequestMapping(value = "/user/picture/", method = RequestMethod.DELETE)
    public ResponseEntity<UserPicture> deleteAllUserPictures() {
        System.out.println("Deleting All UserPictures");
 
        for(UserPicture item: userPictureService.listAllUserPictures())
        {
        	userPictureService.deleteUserPicture(item.getUser_picture_id());
        }
        return new ResponseEntity<UserPicture>(HttpStatus.NO_CONTENT);
    }
}
