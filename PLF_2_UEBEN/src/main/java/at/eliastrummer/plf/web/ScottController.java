package at.eliastrummer.plf.web;

import at.eliastrummer.plf.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/scottdb")
@SessionAttributes("selectedDept")
public class ScottController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SalgradeRepository salgradeRepository;

    @ModelAttribute
    public void onPrepare(Model model) {
        if (!model.containsAttribute("depts")) {
            model.addAttribute("depts", departmentRepository.findAll());
        }

        if (!model.containsAttribute("selectedDept")) {
            model.addAttribute("selectedDept", "");
        }

        if (!model.containsAttribute("emp")) {
            model.addAttribute("emp", new Employee());
        }

        if (!model.containsAttribute("newEmployee")) {
            model.addAttribute("newEmployee", new Employee());
        }
    }

    @GetMapping
    public String showView(@ModelAttribute("selectedDept") String selectedDepartment, Model model) {
        model.addAttribute("employees", new ArrayList<>());
        model.addAttribute("salgrades", new HashMap<>());

        if (!selectedDepartment.equals("")) {
            loadData(selectedDepartment, model);
        }

        return "scottView";
    }

    @PostMapping
    public String loadEmployees(String selectedDept, Model model) {
        loadData(selectedDept, model);
        return "scottView";
    }

    private void loadData(String selectedDept, Model model) {
        Department dept = departmentRepository.findById(Integer.parseInt(selectedDept)).get();
        List<Employee> sortedEmployees = new ArrayList<>(dept.getEmployees());
        sortedEmployees.sort(Comparator.comparing(Employee::getLastname));

        Map<Employee, Salgrade> employeeSalgrades = new HashMap<>();

        for (Employee employee : sortedEmployees) {
            employeeSalgrades.put(employee, salgradeRepository.getSalgradeForEmployee(employee.getEmpNo()));
        }

        model.addAttribute("employees", sortedEmployees);
        model.addAttribute("salgrades", employeeSalgrades);
        model.addAttribute("selectedDept", selectedDept);
    }

    @PostMapping("/addEmp")
    public String addEmployee(@ModelAttribute("newEmployee") Employee employee, Model model) {
        employee.setHireDate(LocalDate.parse(employee.getRawHireDate(), Employee.DTF));
        Department dept = departmentRepository.findById(Integer.parseInt((String) model.getAttribute("selectedDept"))).get();

        employee.setDepartment(dept);
        employeeRepository.save(employee);

        model.addAttribute("selectedDept", model.getAttribute("selectedDept"));
        model.addAttribute("newEmployee", new Employee());

        return "redirect:/scottdb";
    }
}
