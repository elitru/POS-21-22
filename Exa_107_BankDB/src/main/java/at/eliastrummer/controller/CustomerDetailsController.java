package at.eliastrummer.controller;

import at.eliastrummer.dao.Account;
import at.eliastrummer.dao.GiroAccount;
import at.eliastrummer.dao.SavingsAccount;
import at.eliastrummer.dao.UpdateError;
import at.eliastrummer.repositories.CustomerRepository;
import at.eliastrummer.repositories.GiroAccountRepository;
import at.eliastrummer.repositories.SavingsAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

import java.text.NumberFormat;
import java.util.Locale;

@Controller
@Slf4j
@RequestMapping("/customer")
@SessionAttributes({
        "error"
})
public class CustomerDetailsController {
    private CustomerRepository customerRepository;
    private GiroAccountRepository giroAccountRepository;
    private SavingsAccountRepository savingsAccountRepository;

    public CustomerDetailsController(CustomerRepository customerRepository, GiroAccountRepository giroAccountRepository, SavingsAccountRepository savingsAccountRepository) {
        this.customerRepository = customerRepository;
        this.giroAccountRepository = giroAccountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
    }

    @GetMapping("/{customerId}")
    public String showDetails(@PathVariable("customerId") Long customerId, Model model) {
        if (customerId == null) {
            return "redirect:/";
        }

        var customer = customerRepository.findById(customerId).orElseThrow(IllegalArgumentException::new);
        var totalBalance = customer.getAccounts().stream().map(Account::getBalance).reduce(Double::sum).get();
        var formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        model.addAttribute("customer", customer);
        model.addAttribute("totalBalance", formatter.format(totalBalance));
        return "customerDetailsView";
    }

    @PostMapping("/{customerId}")
    public String updateBalance(@PathVariable("customerId") Long customerId, @RequestParam("amount") double amount, @RequestParam("accountId") Long accountId, @RequestParam("type") String type, Model model) {
        log.info("Update...");
        log.info("Amount: " + amount);
        log.info("Account Id: " + accountId);
        log.info("Type: " + type);
        clearErrorMessage(model);

        var giroAccount = giroAccountRepository.findById(accountId);

        if (type.equalsIgnoreCase("withdrawal")) {
            amount *= -1;
        }

        if (giroAccount.isPresent()) {
            var account = giroAccount.get();

            try {
                log.info("Updating...");
                this.updateGiroAccount(account, amount);
            }catch(RuntimeException ex) {
                setErrorMessage(ex, account, model);
            }
        }else{
            var account = savingsAccountRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);

            try {
                this.updateSavingsAccount(account, amount);
            }catch(RuntimeException ex) {
                setErrorMessage(ex, account, model);
            }
        }

        return "redirect:/customer/" + customerId;
    }

    private void setErrorMessage(RuntimeException ex, Account account, Model model) {
        model.addAttribute("error", new UpdateError(account, ex.getMessage(), true));
    }

    private void clearErrorMessage(Model model) {
        model.addAttribute("error" , new UpdateError(null, null, false));
    }

    private void updateGiroAccount(GiroAccount account, double amount) {
        var overdraft = account.getOverdraft() != null ? account.getOverdraft() : 0;
        if ((account.getBalance() + amount + overdraft) < 0) {
            throw new RuntimeException("Withdrawal of € " + String.format("%.2f", Math.abs(amount)) + " for this account not possible.");
        }

        account.setBalance(account.getBalance() + amount);
        giroAccountRepository.save(account);
    }

    private void updateSavingsAccount(SavingsAccount account, double amount) {
        if ((account.getBalance() + amount) < 0) {
            throw new RuntimeException("Withdrawal of € " + String.format("%.2f", Math.abs(amount)) + " for this account not possible.");
        }

        account.setBalance(account.getBalance() + amount);
        savingsAccountRepository.saveAndFlush(account);
    }
}