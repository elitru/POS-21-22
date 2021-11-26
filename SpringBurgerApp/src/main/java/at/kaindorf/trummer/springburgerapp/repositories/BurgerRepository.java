package at.kaindorf.trummer.springburgerapp.repositories;

import at.kaindorf.trummer.springburgerapp.pojos.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {
    //@Query("select b from Burger b where b.name = ?1 order by b.id") // -> muss nicht annotiert sein; einfügen mit alt+enter "Extract ... configure"
    List<Burger> findByNameOrderById(String name);

    List<Burger> findDistinctByName(String name);
}