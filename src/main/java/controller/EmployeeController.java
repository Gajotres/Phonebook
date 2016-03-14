package controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.EmployeeService;
import model.Employee;
import dao.EmployeeDao;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
    @Autowired
    EmployeeService es;

    @Autowired
    EmployeeDao eDao;    
    
    @RequestMapping("/all")
    public Iterable<Employee> getAll () {
        return eDao.findAll();
    }
     
    @RequestMapping("/term/{term}")
    public Iterable<Employee> getEmployee(@PathVariable("term") String term) {
    	return eDao.findEmployeesBySearchTerm(term, term, term, term, term, term, term, term, term);
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public String updateMobileTelephoneNumber(long id, String mobilephoneNumber, String telephoneNumber) {
        try {
        	Employee employee = eDao.findOne(id);
        	employee.setMobilephoneNumber(mobilephoneNumber);
        	employee.setTelephoneNumber(telephoneNumber);;
        	eDao.save(employee);
          } catch (Exception ex) {
            return "Error updating the employee: " + ex.toString();
          }
          return "Employee succesfully updated!";
    }   
}