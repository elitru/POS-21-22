package at.kaindorf.trummer.springburgerapp.repositories;

import at.kaindorf.trummer.springburgerapp.pojos.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class InitDB {
    @Autowired
    private IngredientRepository ingredientRepository;

    @PostConstruct
    public void initIngredients() {
        List<Ingredient> availableIngredients = Arrays.asList(
                new Ingredient("120B", "120g Ground Beef", Ingredient.Type.PATTY),
                new Ingredient("160B", "160g Ground Beef", Ingredient.Type.PATTY),
                new Ingredient("140T", "140g Turkey", Ingredient.Type.PATTY),

                new Ingredient("TOMA", "Tomatoes", Ingredient.Type.VEGGIE),
                new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE),
                new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE),

                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("GOUD", "Gouda", Ingredient.Type.CHEESE)
        );

        ingredientRepository.saveAll(availableIngredients);
    }
}
