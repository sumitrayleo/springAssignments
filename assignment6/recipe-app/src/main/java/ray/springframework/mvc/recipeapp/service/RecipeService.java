package ray.springframework.mvc.recipeapp.service;

import ray.springframework.mvc.recipeapp.domain.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();
}
