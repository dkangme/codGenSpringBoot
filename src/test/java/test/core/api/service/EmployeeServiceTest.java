package test.core.api.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.exception.CannotDeleteEmployeeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDeleteEmployee() throws CannotDeleteEmployeeException {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(anyLong());
    }
    @Test
    public void testDeleteEmployeeException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        assertThrows(CannotDeleteEmployeeException.class, () -> {
            employeeService.deleteEmployee(1L);
        });
    }
    @Test
    public void testGetEmployeesBornBefore2000() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        Date date = calendar.getTime();
        employeeService.getEmployeesBornBefore2000();
        verify(employeeRepository, times(1)).findEmployeesBornBefore(date);
    }
}
