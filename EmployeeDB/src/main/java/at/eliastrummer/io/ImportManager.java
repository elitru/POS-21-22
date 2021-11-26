package at.eliastrummer.io;

import at.eliastrummer.pojo.Department;
import at.eliastrummer.pojo.Employee;
import at.eliastrummer.repositories.DepartmentRepository;
import at.eliastrummer.repositories.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ImportManager {
    private static final File IMPORT_FILE = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "employeedb.json").toFile();

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void loadAndImportData() throws IOException {
        List<Department> departments = new ObjectMapper().readValue(IMPORT_FILE, new TypeReference<List<Department>>() { });
        List<Employee> employees = departments
                .stream()
                .map(Department::getEmployees)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        departments.forEach(d -> employees.add(d.getDeptManager()));

        employeeRepository.saveAll(employees);

        departments.forEach(d -> {
            d.getEmployees().forEach(e -> e.setDepartment(d));
            d.getDeptManager().setDepartment(d);
        });

        departmentRepository.saveAll(departments);
        employeeRepository.saveAll(employees);
    }
}