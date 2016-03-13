package dao;

import model.Employee;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Long> {

  public Employee findByUserId(String userId);
  
  public Iterable<Employee> findByFirstNameContainingOrLastNameContainingOrFullNameContainingOrOrganizationNameContainingOrSubOrganizationNameContainingOrLocationContainingOrJobTitleContainingOrJobTypeContainingAllIgnoreCase(String firstName, String lastName, String fullName, String organizationName, String subOrganizationName, String location, String jobTitle, String jobType);

}