package at.eliastrummer.plf.pojos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalgradeRepository extends JpaRepository<Salgrade, Integer> {
    @Query("SELECT s FROM emp e INNER JOIN salgrade s ON e.salary BETWEEN s.losal AND s.hisal WHERE e.empNo = :empNo")
    Salgrade getSalgradeForEmployee(int empNo);
}