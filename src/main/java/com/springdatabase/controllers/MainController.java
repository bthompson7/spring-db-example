package com.springdatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

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
	  
	
	  System.out.println(userRepository.findByName(name));
	  
	  if(userRepository.findByName(name) == null) {
		  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	  }
	  
	  return userRepository.findByName(name);
	  
  }
  
  //get user by id
  @GetMapping(path="/getID")
  public @ResponseBody User findID(@RequestParam int id) {
	  
	  System.out.println(userRepository.findById(id));

	  if(userRepository.findById(id) == null) {
		  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	  }
	  
	  return userRepository.findById(id);
  }
  
  
  //update a users information
  @PostMapping(path="/updateUser")
  public @ResponseBody int updateUser(@RequestParam int id,@RequestParam String name,
		  @RequestParam String email) {
	  
	  ValidateUser u = new ValidateUser();
		 
		boolean isNameValid = u.isNameValid(name);
		boolean isEmailValid = u.isEmailValid(email);
		
		if(!isNameValid) {
			return -1;
		}
		
		if(!isEmailValid) {
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