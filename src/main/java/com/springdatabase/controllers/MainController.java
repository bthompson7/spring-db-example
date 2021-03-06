package com.springdatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springdatabase.model.User;
import com.springdatabase.repository.UserRepository;
import com.springdatabase.validation.ValidateUser;

@Controller
@RequestMapping(path="/api/v1")
public class MainController {
	
  @Autowired
  private UserRepository userRepository;

  //add a new user to the database
  @PostMapping(path="/add")
  public @ResponseBody String addNewUser 
  (@RequestParam String name
      , @RequestParam String email) {

	  
	ValidateUser u = new ValidateUser();
	 
	boolean isNameValid = u.isNameValid(name);
	boolean isEmailValid = u.isEmailValid(email);
	
	if(!isNameValid) {
		return "invalid name or email";
	}
	
	if(!isEmailValid) {
		return "invalid name or email";
	}
	
	
    User newUser = new User();
    newUser.setName(name);
    newUser.setEmail(email);
    userRepository.save(newUser);
    return "Added user to the database";
  }

  //get all users
  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
  
  
  //get user all users that match a name
  @GetMapping(path="/getUsers")
  public @ResponseBody Iterable<User> findUser(@RequestParam String name) {
	  return userRepository.findByName(name);
	  
  }
  
  //get user by id
  @GetMapping(path="/getID")
  public @ResponseBody Iterable<User> findID(@RequestParam int id) {
	  return userRepository.findById(id);
  }
  
  
  //update a users information
  @PostMapping(path="/updateUser")
  public @ResponseBody int updateUser(@RequestParam int id,@RequestParam String name,
		  @RequestParam String email) {
	  
	  ValidateUser u = new ValidateUser();
		 
		boolean isNameValid = u.isNameValid(name);
		boolean isEmailValid = u.isEmailValid(email);
		
		if(isNameValid == false) {
			return -1;
		}
		
		if(isEmailValid == false) {
			return -1;
		}
		
	  
	  return userRepository.updateUser(id,name,email);
	  
  }
  
  //delete a user
  @PostMapping(path="/deleteUser")
  public @ResponseBody int updateUser(@RequestParam int id) {
	  return userRepository.deleteUser(id);
	  
  }
  
  
  
  
}