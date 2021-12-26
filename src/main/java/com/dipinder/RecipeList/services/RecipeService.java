package com.dipinder.RecipeList.services;

import java.awt.print.Printable;

/*
NTS
-----------------------------------------------------------------
Recipe = Name of Model in Pascal
recipe = name of model in lower case
 ----------------------------------------------------------------
*/


import java.util.List;
import java.util.Optional;

import javax.swing.ListModel;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dipinder.RecipeList.models.Recipe;
import com.dipinder.RecipeList.models.User;
import com.dipinder.RecipeList.repos.RecipeRepository;
import com.dipinder.RecipeList.repos.UserRepository;



@Service
public class RecipeService {

// adding the recipe repository as a dependency
@Autowired
private RecipeRepository recipeRepository;

@Autowired
private UserRepository userRepository;

// returns all the recipe
public List<Recipe> allRecipes() {
    return recipeRepository.findAll();
}

// creates a recipe
public Recipe createRecipe(Recipe b) {
    return recipeRepository.save(b);
}

// retrieves a recipe
public Recipe findRecipe(Long id) {
    Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
    if(optionalRecipe.isPresent()) {
        return optionalRecipe.get();
    } else {
        return null;
    }
}

public Recipe updateRecipe(Recipe b) {
    return recipeRepository.save(b);
}

public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
}

public List<Recipe> findUsersRecipes(Long id){
	Optional<User> creator = userRepository.findById(id);
	return recipeRepository.findByCreator(creator.get());
}

}