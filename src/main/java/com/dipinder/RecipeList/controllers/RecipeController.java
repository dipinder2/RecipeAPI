package com.dipinder.RecipeList.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dipinder.RecipeList.models.Recipe;
import com.dipinder.RecipeList.models.User;
import com.dipinder.RecipeList.services.RecipeService;
import com.dipinder.RecipeList.services.UserService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
@Autowired
private RecipeService recipeService;
@Autowired
private UserService userService;


@GetMapping("")
public List<Recipe> getAll(){
	return recipeService.allRecipes();
}

@RequestMapping(value="", method=RequestMethod.POST)
public Recipe create(@Valid @RequestBody Recipe recipe) {
	User creator = userService.findUser(recipe.getUserId());
	return recipeService.createRecipe(recipe);
}

@GetMapping("/{id}")
public Recipe  show(@PathVariable("id") Long id) {
    return recipeService.findRecipe(id);
}


@PutMapping("/{id}")
public Recipe update(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe) {
	recipe.setId(id);
	return recipeService.updateRecipe(recipe);
}

@DeleteMapping("/{id}")
public void destroy(@PathVariable("id") Long id) {
    recipeService.deleteRecipe(id);
}

}