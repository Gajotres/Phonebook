package dao;

import model.Employee;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Long> {

  public Employee findByUserId(String userId);
    
  @Query("SELECT p.id, p.firstName, p.lastName, p.mobilephoneNumber, p.telephoneNumber, p.organizationName, p.subOrganizationName FROM Employee p WHERE p.firstName like %:firstName% OR p.lastName LIKE %:lastName% OR p.organizationName LIKE %:organizationName% OR p.subOrganizationName LIKE %:subOrganizationName% OR p.jobTitle LIKE %:jobTitle% OR p.mobilephoneNumber LIKE %:mobilephoneNumber% OR p.telephoneNumber LIKE %:telephoneNumber% OR p.userId LIKE %:userId% OR p.location LIKE %:location%")
  public Iterable<Employee> findEmployeesBySearchTerm(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("organizationName") String organizationName, @Param("subOrganizationName") String subOrganizationName, @Param("jobTitle") String jobTitle, @Param("mobilephoneNumber") String mobilephoneNumber, @Param("telephoneNumber") String telephoneNumber, @Param("userId") String userId, @Param("location") String location);  
}