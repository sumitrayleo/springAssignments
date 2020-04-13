package ray.springframework.mvc.recipeapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ray.springframework.mvc.recipeapp.domain.Difficulty;
import ray.springframework.mvc.recipeapp.domain.Ingredient;
import ray.springframework.mvc.recipeapp.domain.Recipe;
import ray.springframework.mvc.recipeapp.repository.CategoryRepository;
import ray.springframework.mvc.recipeapp.repository.RecipeRepository;
import ray.springframework.mvc.recipeapp.repository.UnitOfMeasureRepository;

import java.math.BigDecimal;

@Component
public class RecipeDataLoader implements CommandLineRunner {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public RecipeDataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        setupPerfectGuacamole();
        setupSpicyGrilledChicken();
    }

    private void setupSpicyGrilledChicken() {
        final Recipe spicyChicken = new Recipe();
        spicyChicken.getIngredients().add(getIngredient("dried cumin", BigDecimal.valueOf(2), "Teaspoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("dried oregano", BigDecimal.valueOf(1), "Teaspoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("sugar", BigDecimal.valueOf(1), "Teaspoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("salt", BigDecimal.valueOf(.5), "Teaspoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("ancho chili powder", BigDecimal.valueOf(2), "Tablespoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("garlic", BigDecimal.valueOf(1), "Clove", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("finely grated orange zest", BigDecimal.valueOf(1), "Tablespoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), "Tablespoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("olive oil", BigDecimal.valueOf(2), "Tablespoon", spicyChicken));
        spicyChicken.getIngredients().add(getIngredient("boneless chicken thighs", BigDecimal.valueOf(11.25), "Pound", spicyChicken));

        spicyChicken.getCategories().add(categoryRepository.findByDescription("American").get());

        spicyChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyChicken.setCookTime(45);
        spicyChicken.setPrepTime(60);
        spicyChicken.setDescription("Spicy grilled chicken tacos");
        spicyChicken.setDifficulty(Difficulty.KIND_OF_HARD);
        spicyChicken.setServing(4);

        recipeRepository.save(spicyChicken);
    }

    private void setupPerfectGuacamole() {
        final Recipe guacamole = new Recipe();
        guacamole.getIngredients().add(getIngredient("ripe avocados", BigDecimal.valueOf(2), "Number", guacamole));
        guacamole.getIngredients().add(getIngredient("salt", BigDecimal.valueOf(.25), "Teaspoon", guacamole));
        guacamole.getIngredients().add(getIngredient("fresh lime juice", BigDecimal.valueOf(1), "Tablespoon", guacamole));
        guacamole.getIngredients().add(getIngredient("minced red onion", BigDecimal.valueOf(.25), "Cup", guacamole));
        guacamole.getIngredients().add(getIngredient("finely chopped cilantro", BigDecimal.valueOf(2), "Tablespoon", guacamole));
        guacamole.getIngredients().add(getIngredient("freshly grated black pepper", BigDecimal.valueOf(1), "Dash", guacamole));
        guacamole.getIngredients().add(getIngredient("ripe tomato", BigDecimal.valueOf(.5), "Number", guacamole));
        guacamole.getIngredients().add(getIngredient("Red radishes", BigDecimal.valueOf(2), "Number", guacamole));
        guacamole.getIngredients().add(getIngredient("Tortilla chips", BigDecimal.valueOf(1), "Packet", guacamole));

        guacamole.getCategories().add(categoryRepository.findByDescription("Mexican").get());

        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setCookTime(20);
        guacamole.setPrepTime(20);
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setDifficulty(Difficulty.MODERATE);
        guacamole.setServing(1);

        recipeRepository.save(guacamole);
    }

    private Ingredient getIngredient(String description, BigDecimal amount, String uom, Recipe recipe) {
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(description);
        ingredient.setAmount(amount);
        ingredient.setUom(unitOfMeasureRepository.findByDescription(uom).get());
        ingredient.setRecipe(recipe);
        return ingredient;
    }
}