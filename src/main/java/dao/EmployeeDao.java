package dao;

import model.Employee;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Long> {

  public Employee findByUserId(String userId);
  
  public Iterable<Employee> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstName, String lastName);

}