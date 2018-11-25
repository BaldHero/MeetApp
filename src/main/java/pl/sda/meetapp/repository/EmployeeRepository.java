package pl.sda.meetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.meetapp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
