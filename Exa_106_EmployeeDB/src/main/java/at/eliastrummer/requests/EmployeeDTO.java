package at.eliastrummer.requests;

import at.eliastrummer.pojo.Department;
import at.eliastrummer.pojo.Employee;
import at.eliastrummer.pojo.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class EmployeeDTO {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @NotBlank(message = "Firstname is required!")
    @Length(min = 3, max = 16, message = "Firstname must be between 3 and 16 characters long.")
    private String firstname;
    @NotBlank(message = "Lastname is required!")
    @Length(min = 3, max = 16, message = "Lastname must be between 3 and 16 characters long.")
    private String lastname;
    @NotBlank(message = "Gender is required!")
    private String gender;
    @NotBlank(message = "Birthdate is required!")
    private String birthdate;

    public Employee toEmployee(Department department) {
        Employee emp =  new Employee(this.firstname, this.lastname, gender.equalsIgnoreCase("m") ? Gender.MALE : Gender.FEMALE, LocalDate.parse(this.birthdate, DTF));
        emp.setDepartment(department);
        return emp;
    }
}