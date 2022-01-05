package at.eliastrummer.repositories;

import at.eliastrummer.dao.GiroAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiroAccountRepository extends JpaRepository<GiroAccount, Long> {
}