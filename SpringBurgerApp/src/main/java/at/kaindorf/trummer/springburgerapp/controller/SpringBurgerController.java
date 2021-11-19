package at.kaindorf.trummer.springburgerapp.controller;

import at.kaindorf.trummer.springburgerapp.pojos.Burger;
import at.kaindorf.trummer.springburgerapp.pojos.Ingredient;
import at.kaindorf.trummer.springburgerapp.repositories.BurgerRepository;
import at.kaindorf.trummer.springburgerapp.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("designBurger")
public class SpringBurgerController {

    private List<Ingredient> availableIngredients;
    private IngredientRepository ingredientRepository;
    @Autowired
    private BurgerRepository burgerRepository;

    public SpringBurgerController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        availableIngredients = ingredientRepository.findAll();
        Map<String, List<Ingredient>> ingredients = new HashMap<>();

        for (Ingredient.Type ingredient : Ingredient.Type.values()) {
            ingredients.put(ingredient.name().toLowerCase(), filterByType(ingredient));
        }

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("designBurger", new Burger());
    }

    private List<Ingredient> filterByType(Ingredient.Type type) {
        return availableIngredients.stream().filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesign() {
        return "designForm";
    }

    @PostMapping
    public String processBurger(@Valid @ModelAttribute("designBurger") Burger burger, Errors errors) {
        log.info(burger.toString());

        if (errors.hasErrors()) {
            log.info(errors.getObjectName() + " -> " + errors.getAllErrors());
            return "designForm";
        }

        burgerRepository.save(burger);
        return "redirect:/orders/current";
    }
}
