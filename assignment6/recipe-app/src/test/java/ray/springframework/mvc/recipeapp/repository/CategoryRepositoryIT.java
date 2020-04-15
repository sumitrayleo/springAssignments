package ray.springframework.mvc.recipeapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ray.springframework.mvc.recipeapp.domain.Category;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a Integration Test case for Category. Showcases the Integration Test feature with spring context
 */

@RunWith(SpringRunner.class)
@DataJpaTest
class CategoryRepositoryIT {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByDescriptionAmerican() {
        Optional<Category> category = categoryRepository.findByDescription("American");
        assertEquals("American", category.get().getDescription());
    }

    @Test
    void findByDescriptionMexican() {
        Optional<Category> category = categoryRepository.findByDescription("Mexican");
        assertEquals("Mexican", category.get().getDescription());
    }
}