package controller;
 
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import service.EmployeeService;
import model.Employee;
import model.SaveEmployee;
import model.User;
import model.UserRequest;
import dao.EmployeeDao;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
    @Autowired
    EmployeeService es;

    @Autowired
    EmployeeDao eDao;    
    
    @CrossOrigin()
    @RequestMapping("/all")
    public Iterable<Employee> getAll () {
        return eDao.findAll();
    }
    
    @CrossOrigin()
    @RequestMapping("/id/{id}")
    public Employee getEmployeeById (@PathVariable("id") int id) {
        return eDao.findById(id);
    }    
    
    @CrossOrigin()
    @RequestMapping(value = "/term/{term}", produces = "application/json")
    public Iterable<Employee> getEmployee(@PathVariable("term") String term) {
    	return  eDao.findEmployeesBySearchTerm(term);
    }
    
    @CrossOrigin()
    @RequestMapping(value = "/letter/{letter}", produces = "application/json")
    public Iterable<Employee> getEmployeeByLetter(@PathVariable("letter") String letter) {
    	return  eDao.findEmployeesByFirstLastNameLetter(letter);
    }    
    
    @CrossOrigin()
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public void updateEmployeeData(@RequestBody @Valid final SaveEmployee saveEmployee) {
    	    	
	    try {
	    	Employee employee = eDao.findOne(saveEmployee.getId());
	    	employee.setMobilephoneNumber(saveEmployee.getMobilephoneNumber());
	    	employee.setTelephoneNumber(saveEmployee.getTelephoneNumber());
	    	employee.setImage(saveEmployee.getImage().replace("data:image/jpeg;base64,", ""));
	    	employee.setSmallImage(saveEmployee.getSmallImage().replace("data:image/jpeg;base64,", ""));	 
	    	eDao.save(employee);
	      } catch (Exception ex) {
	    	System.out.println("Error updating the employee: " + ex.toString());  
	      }
    }      
}