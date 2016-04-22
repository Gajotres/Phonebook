package dao;

import model.Employee;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

  public Employee findByUserId(String userId);
  
  public Employee findById(int id);
    
  @Query("SELECT new model.Employees(p.id, p.firstName, p.lastName, p.organizationName, p.subOrganizationName, p.mobilephoneNumber, p.telephoneNumber, p.smallImage) FROM Employee p WHERE UPPER(p.firstName) like UPPER(%:term%) OR UPPER(p.lastName) LIKE UPPER(%:term%) OR UPPER(p.organizationName) LIKE UPPER(%:term%) OR UPPER(p.subOrganizationName) LIKE UPPER(%:term%) OR UPPER(p.jobTitle) LIKE UPPER(%:term%) OR p.mobilephoneNumber LIKE %:term% OR p.telephoneNumber LIKE %:term% OR p.userId LIKE %:term% OR UPPER(p.location) LIKE UPPER(%:term%) ORDER by p.lastName")
  public Iterable<Employee> findEmployeesBySearchTerm(@Param("term") String term);
  
  @Query("SELECT new model.Employees(p.id, p.firstName, p.lastName, p.organizationName, p.subOrganizationName, p.mobilephoneNumber, p.telephoneNumber, p.smallImage) FROM Employee p WHERE p.lastName LIKE :letter% ORDER by p.lastName")
  public Iterable<Employee> findEmployeesByFirstLastNameLetter(@Param("letter") String letter);  
}