package controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.OrgUnit;
import dao.OrgUnitsDao;
 
@RestController
@RequestMapping("/orgunits")
public class OrgUnitsController {
	
    @Autowired
    OrgUnitsDao oDao;    
        
    @CrossOrigin()
    @RequestMapping("/get")
    public Iterable<OrgUnit> getOrgUnits() {
        return oDao.findAll();
    }       
}