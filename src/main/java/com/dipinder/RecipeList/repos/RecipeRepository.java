package com.dipinder.RecipeList.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dipinder.RecipeList.models.Recipe;
import com.dipinder.RecipeList.models.User;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
// this method retrieves all the Users from the database
List<Recipe> findAll();
List<Recipe> findByCreator(User creator);
}