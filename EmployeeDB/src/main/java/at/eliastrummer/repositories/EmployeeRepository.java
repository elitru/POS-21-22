package at.eliastrummer.repositories;

import at.eliastrummer.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.department.deptNo = ?1")
    List<Employee> findEmployeesByDepartment(int departmentId);

    @Modifying
    @Query("delete from Employee e where e.employeeNo = ?1")
    void deleteByEmployeeNo(int employeeNo);
}