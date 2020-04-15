package ray.springframework.mvc.recipeapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ray.springframework.mvc.recipeapp.domain.Recipe;
import ray.springframework.mvc.recipeapp.service.RecipeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    /**
     * This class will show SpringMvcTest, where we can easily test our controllers
     * @throws Exception
     */
    @Test
    public void testRecipeControllerMvc() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/list"));
    }


    /**
     * This test shows the capability of -
     *         a) Mockito
     *         b) ArgumentCaptor
     *         c) JUnit 5
     * @throws Exception
     */
    @Test
    void getAllRecipes() throws Exception{
        // given
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(1L);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        // when
        when(recipeService.findAll()).thenReturn(recipes);
        String returnVal = recipeController.getAllRecipes(model);
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // then
        assertEquals("recipe/list", returnVal);
        verify(recipeService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        List<Recipe> listOfRecipes = argumentCaptor.getValue();
        assertEquals(2, listOfRecipes.size());
    }
}