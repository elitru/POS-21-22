package at.kaindorf.trummer.springburgerapp.controller;

import at.kaindorf.trummer.springburgerapp.pojos.Burger;
import at.kaindorf.trummer.springburgerapp.pojos.Order;
import at.kaindorf.trummer.springburgerapp.repositories.BurgerRepository;
import at.kaindorf.trummer.springburgerapp.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class SpringOrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @RequestMapping("/current")
    public String requestCurrentOrder(Model model, @SessionAttribute Burger designBurger) {
        log.info("your burger" + designBurger);

        model.addAttribute("designBurger", designBurger);
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String performOrder(@Valid @ModelAttribute Order order,  @SessionAttribute Burger designBurger, Errors errors) {

        if (errors.hasErrors()) {
            log.info(errors.getObjectName() + " " + errors.getAllErrors());
            return "orderForm";
        }
        log.info("ordered smth" + order);
        order.addBurger(designBurger);
        orderRepository.save(order);
        burgerRepository.saveAndFlush(designBurger);
        return "redirect:/design";
    }
}