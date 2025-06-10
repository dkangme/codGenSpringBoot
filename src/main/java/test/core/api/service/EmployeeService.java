package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import java.util.List;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    // Inicio de cambio realizado por la IA
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        if (employee.getGender().equalsIgnoreCase("Femenino")) {
            throw new RuntimeException("Cannot delete female employees");
        }
        employeeRepository.delete(employee);
    }
    public List<Employee> getEmployeesBornBeforeYear(int year) {
        return employeeRepository.findBornBeforeYear(year);
    }
    public List<Employee> getEmployeesOrderByPosition() {
        return employeeRepository.findAllOrderByPosition();
    }
    // Fin de cambio realizado por la IA
}
