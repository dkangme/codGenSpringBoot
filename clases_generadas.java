package test.core.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.core.api.model.Employee;
import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Inicio de cambio realizado por la IA
    @Query("SELECT e FROM Employee e WHERE e.gender = :gender")
    List<Employee> findByGender(@Param("gender") String gender);
    @Query("SELECT e FROM Employee e WHERE YEAR(e.birthDate) < :year")
    List<Employee> findBornBeforeYear(@Param("year") int year);
    @Query("SELECT e FROM Employee e ORDER BY e.position")
    List<Employee> findAllOrderByPosition();
    // Fin de cambio realizado por la IA
}
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
package test.core.api.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.model.Employee;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    // Inicio de cambio realizado por la IA
    @Test
    void deleteEmployee() {
        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(1L));
    }
    @Test
    void getEmployeesBornBeforeYear() {
        List<Employee> employees = employeeService.getEmployeesBornBeforeYear(2000);
        assertTrue(employees.stream().allMatch(e -> e.getBirthDate().getYear() < 2000));
    }
    @Test
    void getEmployeesOrderByPosition() {
        List<Employee> employees = employeeService.getEmployeesOrderByPosition();
        assertTrue(employees.stream().sorted((e1, e2) -> e1.getPosition().compareTo(e2.getPosition())).equals(employees));
    }
    // Fin de cambio realizado por la IA
}
package test.core.api.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.core.api.model.Employee;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    // Inicio de cambio realizado por la IA
    @Test
    void deleteEmployee() {
        ResponseEntity<String> response = restTemplate.exchange("/api/employees/1", HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void getEmployeesBornBeforeYear() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/employees/born-before/2000", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void getEmployeesOrderByPosition() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/employees/order-by-position", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    // Fin de cambio realizado por la IA
}
