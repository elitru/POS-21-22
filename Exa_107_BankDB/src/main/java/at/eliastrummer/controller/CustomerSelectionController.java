package at.eliastrummer.controller;

import at.eliastrummer.repositories.CustomerRepository;
import at.eliastrummer.repositories.GiroAccountRepository;
import at.eliastrummer.repositories.SavingsAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("")
public class CustomerSelectionController {
    private CustomerRepository customerRepository;

    public CustomerSelectionController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String showOverview(@RequestParam(value = "q", required = false) String lastname, Model model) {
        if (lastname == null) {
            lastname = "";
        }

        var customers = customerRepository.findCustomersByLastnameOrdered(lastname);
        model.addAttribute("customers", customers);
        model.addAttribute("query", lastname);
        return "customerSelectionView";
    }
}