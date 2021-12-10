package at.eliastrummer.controller;

import at.eliastrummer.pojo.Department;
import at.eliastrummer.pojo.Employee;
import at.eliastrummer.repositories.DepartmentRepository;
import at.eliastrummer.repositories.EmployeeRepository;
import at.eliastrummer.requests.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
@RequestMapping("/employees")
@SessionAttributes({
        "selectedDepartment",
        "sortActive",
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

        if (model.getAttribute("selectedDepartment") == null) {
            model.addAttribute("selectedDepartment", departments.get(0));
        }
    }

    @GetMapping
    public String showEmployees(@RequestParam(name = "dept", required = false) String deptNo, @RequestParam(name = "sort", required = false) String applySort, Model model) {
        List<Department> departments = departmentRepository.findAll();
        Department selectedDept = deptNo == null || deptNo.equals("") ? (Department) model.getAttribute("selectedDepartment") : departments.stream().filter(d -> d.getDeptNo() == Integer.parseInt(deptNo)).findFirst().get();

        model.addAttribute("selectedDepartment", selectedDept);

        List<Employee> employees = employeeRepository.findEmployeesByDepartment(selectedDept.getDeptNo());

        if (applySort != null && applySort.equals("apply")) {
            employees.sort(Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname));
            model.addAttribute("sortActive", true);
        }else{
            model.addAttribute("sortActive", false);
        }

        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/new")
    public String addEmployeeView(@SessionAttribute Department selectedDepartment, Model model) {
        model.addAttribute("selectedDepartment", selectedDepartment);
        model.addAttribute("newEmployee", new EmployeeDTO());
        return "addEmployee";
    }

    @PostMapping("/new")
    @Transactional
    public String addNewEmployee(@Valid @ModelAttribute("newEmployee") EmployeeDTO employeeDTO, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "addEmployee";
        }

        Department dept = (Department) model.getAttribute("selectedDepartment");
        Employee employee = employeeDTO.toEmployee(dept);

        employeeRepository.save(employee);
        return getRoute(model);
    }

    @PostMapping("/delete")
    @Transactional
    public String processRequest(@RequestParam("employeeNo") String employeeNo, Model model) {
        employeeRepository.deleteByEmployeeNo(Integer.parseInt(employeeNo));
        return getRoute(model);
    }

    private String getRoute(Model model) {
        return "redirect:/employees?dept=" + ((Department) model.getAttribute("selectedDepartment")).getDeptNo() + ((boolean) model.getAttribute("sortActive") ? "&sort=apply" : "");
    }
}