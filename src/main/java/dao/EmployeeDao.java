package dao;

import model.Employee;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Long> {

  public Employee findByUserId(String userId);
  
  public Employee findById(int id);
    
  @Query("SELECT new model.Employees(p.id, p.firstName, p.lastName, p.organizationName, p.subOrganizationName, p.mobilephoneNumber, p.telephoneNumber, p.smallImage) FROM Employee p WHERE p.firstName like %:term% OR p.lastName LIKE %:term% OR p.organizationName LIKE %:term% OR p.subOrganizationName LIKE %:term% OR p.jobTitle LIKE %:term% OR p.mobilephoneNumber LIKE %:term% OR p.telephoneNumber LIKE %:term% OR p.userId LIKE %:term% OR p.location LIKE %:term%")
  public Iterable<Employee> findEmployeesBySearchTerm(@Param("term") String term);  
}