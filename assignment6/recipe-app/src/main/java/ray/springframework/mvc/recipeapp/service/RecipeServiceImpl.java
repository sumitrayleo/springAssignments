package ray.springframework.mvc.recipeapp.service;

import org.springframework.stereotype.Service;
import ray.springframework.mvc.recipeapp.domain.Recipe;
import ray.springframework.mvc.recipeapp.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
