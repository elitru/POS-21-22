package at.eliastrummer.controller;

import at.eliastrummer.pojo.Department;
import at.eliastrummer.repositories.DepartmentRepository;
import at.eliastrummer.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@Slf4j
@RequestMapping("/employees")
@SessionAttributes({
        "departments",
        "selectedDepartment",
        "employees"
})
public class EmployeeController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("selectedDepartment", departments.get(0));
        model.addAttribute("employees", employeeRepository.findEmployeesByDepartment(departments.get(0).getDeptNo()));
    }

    @GetMapping
    public String showEmployees() {
        return "employees";
    }

    @PostMapping
    public String processRequest(@SessionAttribute("selectedDepartment") Department department, Model model) {
        log.info(department.toString());
        model.addAttribute("employees", employeeRepository.findEmployeesByDepartment(department.getDeptNo()));
        return "redirect:/employees";
    }
}