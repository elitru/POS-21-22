package at.eliastrummer.controller;

import at.eliastrummer.dao.Address;
import at.eliastrummer.repositories.AccountRepository;
import at.eliastrummer.repositories.AddressRepository;
import at.eliastrummer.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/statistics")
public class StatisticController {
    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public String showStatistics(@RequestParam(name = "city", required = false) String city, Model model) {
        var averageBalance = this.accountRepository.getAverageNegativeBalance();
        var numberAccounts = this.accountRepository.getAmountOfAccountsWithMoreThanOneOwner();
        var highestBalance = this.accountRepository.getByHighestBalance();
        var lowestBalance = this.accountRepository.getByLowestBalance();
        var percentageMale = this.customerRepository.getPercentageOfMaleCustomers();
        var percentageFemale = this.customerRepository.getPercentageOfFemaleCustomers();
        var cities = this.addressRepository.findAll().stream().map(Address::getCity).collect(Collectors.toList());

        city = city == null ? cities.get(0) : city;

        model.addAttribute("averageNegativeBalance", FORMATTER.format(averageBalance));
        model.addAttribute("numberAccountsMoreThanOne", numberAccounts);
        model.addAttribute("highestBalance", highestBalance.get(0));
        model.addAttribute("lowestBalance", lowestBalance.get(0));
        model.addAttribute("percentageMale", String.format("%.2f", percentageMale) + "%");
        model.addAttribute("percentageFemale", String.format("%.2f", percentageFemale) + "%");
        model.addAttribute("cities", cities);
        model.addAttribute("selectedCity", city);

        model.addAttribute("customers", customerRepository.findCustomersByCity(city));

        return "statisticView";
    }
}
