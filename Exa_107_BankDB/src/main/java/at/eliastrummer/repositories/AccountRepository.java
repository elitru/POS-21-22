package at.eliastrummer.repositories;

import at.eliastrummer.dao.Account;
import at.eliastrummer.dao.GiroAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT AVG(a.balance) FROM Account a WHERE a.balance < 0")
    double getAverageNegativeBalance();

    @Query(value = "SELECT COUNT(a) FROM ( SELECT customer_id, COUNT(account_id) FROM customer_account GROUP BY customer_id HAVING COUNT(account_id) > 1) a;", nativeQuery = true)
    Long getAmountOfAccountsWithMoreThanOneOwner();

    @Query("SELECT a FROM Account a WHERE a.balance = (SELECT MAX(a2.balance) FROM Account a2)")
    List<Account> getByHighestBalance();

    @Query("SELECT a FROM Account a WHERE a.balance = (SELECT MIN(a2.balance) FROM Account a2)")
    List<Account> getByLowestBalance();
}