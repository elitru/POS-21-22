package at.kaindorf.trummer.springburgerapp.controller;

import at.kaindorf.trummer.springburgerapp.pojos.Burger;
import at.kaindorf.trummer.springburgerapp.pojos.Ingredient;
import lombok.extern.slf4j.Slf4j;
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

    private List<Ingredient> availableIngredients = Arrays.asList(
            new Ingredient("120B", "120g Ground Beef", Ingredient.Type.PATTY),
            new Ingredient("160B", "160g Ground Beef", Ingredient.Type.PATTY),
            new Ingredient("140T", "140g Turkey", Ingredient.Type.PATTY),

            new Ingredient("TOMA", "Tomatoes", Ingredient.Type.VEGGIE),
            new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE),
            new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE),

            new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            new Ingredient("GOUD", "Gouda", Ingredient.Type.CHEESE)
    );

    @ModelAttribute
    public void addAttributes(Model model) {
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

        return "redirect:/orders/current";
    }
}
