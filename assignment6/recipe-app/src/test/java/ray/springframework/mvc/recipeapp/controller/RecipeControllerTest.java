package ray.springframework.mvc.recipeapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ray.springframework.mvc.recipeapp.service.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    void getAllRecipes() {
        assertEquals("recipe/list", recipeController.getAllRecipes(model));

        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());
    }
}