package service;
 
import java.util.Hashtable;
import org.springframework.stereotype.Service;
import model.Employee;;
 
@Service
public class EmployeeService {

    Hashtable <String, Employee> zaposlenici = new Hashtable<String, Employee>(); 
     
    public EmployeeService() {
 
    }
     
    public Employee getEmployee(String id) {
        if(zaposlenici.containsKey(id)) {
            return zaposlenici.get(id);
        } else {
            return null;
        }
    }
     
    public Hashtable<String, Employee> getAll () {
        return zaposlenici;
    }
}