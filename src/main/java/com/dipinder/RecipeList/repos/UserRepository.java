package com.dipinder.RecipeList.repos;

/*
NTS
-----------------------------------------------------------------
User = Name of Model in Pascal
 ----------------------------------------------------------------
*/

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dipinder.RecipeList.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
// this method retrieves all the Users from the database
List<User> findAll();
User findByEmail(String email);
}