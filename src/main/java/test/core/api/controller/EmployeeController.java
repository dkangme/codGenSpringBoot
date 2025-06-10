package test.core.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // Inicio de cambio realizado por la IA
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/born-before/{year}")
    public List<Employee> getEmployeesBornBeforeYear(@PathVariable int year) {
        return employeeService.getEmployeesBornBeforeYear(year);
    }
    @GetMapping("/order-by-position")
    public List<Employee> getEmployeesOrderByPosition() {
        return employeeService.getEmployeesOrderByPosition();
    }
    // Fin de cambio realizado por la IA
}
