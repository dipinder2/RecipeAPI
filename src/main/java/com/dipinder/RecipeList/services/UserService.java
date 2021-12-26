package com.dipinder.RecipeList.services;

/*
NTS
-----------------------------------------------------------------
User = Name of Model in Pascal
user = name of model in lower case
 ----------------------------------------------------------------
*/


import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipinder.RecipeList.models.User;
import com.dipinder.RecipeList.repos.UserRepository;



@Service
public class UserService {

// adding the user repository as a dependency
@Autowired
private UserRepository userRepository;

// returns all the user
public List<User> allUsers() {
    return userRepository.findAll();
}

// creates a user
public User createUser(User b) {
    return userRepository.save(b);
}


// register user and hash their password
public User registerUser(User user) {
    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    user.setPassword(hashed);
    return userRepository.save(user);
}

// find user by email
public User findByEmail(String email) {
    return userRepository.findByEmail(email);
}
// authenticate user
public User authenticateUser(String email, String password) {
    // first find the user by email
    User user = userRepository.findByEmail(email);
    // if we can't find it by email, return false
    if(user == null) {
        return user;
    } else {
        // if the passwords match, return true, else, return false
        if(BCrypt.checkpw(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}

// retrieves a user
public User findUser(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if(optionalUser.isPresent()) {
        return optionalUser.get();
    } else {
        return null;
    }
}

public void deleteUser(Long id) {
    userRepository.deleteById(id);
}

}