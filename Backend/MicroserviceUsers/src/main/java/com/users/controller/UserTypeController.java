package com.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.users.model.UserType;
import com.users.service.UserTypeService;

@Controller
@RequestMapping(value="/api")
public class UserTypeController 
{
	@Autowired
	private UserTypeService userTypeService;
	
	//-------------------Retrieve All UserTypes--------------------------------------------------------
    
    @RequestMapping(value = "/user/type", method = RequestMethod.GET)
    public ResponseEntity<List<UserType>> listAllUserTypes() {
        List<UserType> userTypes = (List<UserType>) userTypeService.listAllUserTypes();
        if(userTypes.isEmpty()){
            return new ResponseEntity<List<UserType>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserType>>(userTypes, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single UserType--------------------------------------------------------
     
    @RequestMapping(value = "/user/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserType> getUserType(@PathVariable("id") Integer id) {
        System.out.println("Fetching UserType with id " + id);
        UserType userType = userTypeService.getUserTypeById(id);
        if (userType == null) {
            System.out.println("UserType with id " + id + " not found");
            return new ResponseEntity<UserType>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserType>(userType, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a UserType--------------------------------------------------------
     
    @RequestMapping(value = "/user/type/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserType(@RequestBody UserType userType, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating UserType " + userType.getUser_type_name());
 
        if (userTypeService.isUserTypeExist(userType)) {
            System.out.println("A UserType with name " + userType.getUser_type_name() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userTypeService.saveUserType(userType);;
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userType.getUser_type_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a UserType --------------------------------------------------------
     
    @RequestMapping(value = "/user/type/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserType> updateUserType(@PathVariable("id") Integer id, @RequestBody UserType userType) {
        System.out.println("Updating UserType " + id);
         
        UserType currentUserType = userTypeService.getUserTypeById(id);
         
        if (currentUserType==null) {
            System.out.println("UserType with id " + id + " not found");
            return new ResponseEntity<UserType>(HttpStatus.NOT_FOUND);
        }
 
        currentUserType.setUser_type_name(userType.getUser_type_name());
         
        userTypeService.updateUserType(currentUserType);;
        return new ResponseEntity<UserType>(currentUserType, HttpStatus.OK);
    }
 
    //------------------- Delete a UserType --------------------------------------------------------
     
    @RequestMapping(value = "/user/type/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserType> deleteUserType(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting UserType with id " + id);
 
        UserType userType = userTypeService.getUserTypeById(id);
        if (userType == null) {
            System.out.println("Unable to delete. UserType with id " + id + " not found");
            return new ResponseEntity<UserType>(HttpStatus.NOT_FOUND);
        }

        userTypeService.deleteUserType(id);
        return new ResponseEntity<UserType>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All UserTypes --------------------------------------------------------
    
    @RequestMapping(value = "/user/type/", method = RequestMethod.DELETE)
    public ResponseEntity<UserType> deleteAllUserTypes() {
        System.out.println("Deleting All UserTypes");
 
        for(UserType item: userTypeService.listAllUserTypes())
        {
        	userTypeService.deleteUserType(item.getUser_type_id());
        }
        return new ResponseEntity<UserType>(HttpStatus.NO_CONTENT);
    }
}
