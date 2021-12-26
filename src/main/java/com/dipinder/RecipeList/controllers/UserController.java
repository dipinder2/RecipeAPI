package com.dipinder.RecipeList.controllers;

import java.awt.print.Printable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dipinder.RecipeList.models.User;
import com.dipinder.RecipeList.services.UserService;
import com.dipinder.RecipeList.validators.UserValidator;


@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;
  
  @Autowired
  private UserValidator userValidator;
  
  
  @InitBinder
  public void setup(WebDataBinder webDataBinder) {
      webDataBinder.addValidators(userValidator);
  }
  
  @GetMapping("")
  public List<User> index() {
      List<User> users = userService.allUsers();
      return users;
  }
  
  @PostMapping("")
  public User create(@Valid @RequestBody User user) {
	  User newUser = userService.registerUser(user);
	  newUser.setPasswordConfirmation(newUser.getPassword());
	  return newUser;
  }
  
  @PostMapping("/login")
  public User login(@RequestBody User user) {
	  return userService.authenticateUser(user.getEmail(), user.getPassword());
  }
  
  @GetMapping("/{id}")
  public User show(@PathVariable("id") Long id) {
      return userService.findUser(id);
  }
  
  
  @PutMapping("/{id}")
  public User edit(@Valid @RequestBody User user, @PathVariable("id") Long id) {
	  user.setId(id);
	  return userService.registerUser(user);
  }
  
  @DeleteMapping(value="/{id}")
  public void destroy(@PathVariable("id") Long id) {
      userService.deleteUser(id);
  }
  
}


