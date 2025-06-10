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
