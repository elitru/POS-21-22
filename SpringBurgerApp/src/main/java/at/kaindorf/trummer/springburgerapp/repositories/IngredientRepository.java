package at.kaindorf.trummer.springburgerapp.repositories;

import at.kaindorf.trummer.springburgerapp.pojos.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}