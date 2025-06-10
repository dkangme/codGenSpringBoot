package test.core.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.core.api.model.Employee;
import java.util.Date;
import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
