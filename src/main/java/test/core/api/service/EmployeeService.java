package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.exception.CannotDeleteEmployeeException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void deleteEmployee(Long id) throws CannotDeleteEmployeeException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete female employee");
        }
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesBornBefore2000() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        Date date = calendar.getTime();
        return employeeRepository.findEmployeesBornBefore(date);
    }
}
