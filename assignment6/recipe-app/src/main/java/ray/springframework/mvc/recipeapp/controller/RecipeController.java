package ray.springframework.mvc.recipeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ray.springframework.mvc.recipeapp.service.RecipeService;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllRecipes(Model model){
        log.info("Inside " + IndexController.class.getSimpleName() + ".getAllRecipes()");
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/list";
    }
}
