package com.springdatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//To test -> curl -d "name=Ben&email=test@gmail.com" -X POST http://localhost:8080/api/v1/add


@Controller // This means that this class is a Controller
@RequestMapping(path="/api/v1") // This means URL's start with /api/v1 (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User newUser = new User();
    newUser.setName(name);
    newUser.setEmail(email);
    userRepository.save(newUser);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the user
    return userRepository.findAll();
  }
  
  
  @GetMapping(path="/user")
  
  public @ResponseBody Iterable<User> findUser(@RequestParam String name) {
	  
	  
	  
	  return userRepository.findByName(name);
	  
  }
  
}