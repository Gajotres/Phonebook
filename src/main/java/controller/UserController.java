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

import model.User;
import model.UserRequest;
import dao.UserDao;
 
@RestController
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    UserDao uDao;    
        
    @CrossOrigin()
    @RequestMapping("/id/{id}")
    public User getEmployeeById (@PathVariable("id") int id) {
        return uDao.findById(id);
    }    
    
    @CrossOrigin()
    @RequestMapping(value = "/authorize",method = RequestMethod.POST)
    @ResponseBody
    public Boolean authorizeUser(@RequestBody @Valid final UserRequest userRequest) {
    	
    	User user = uDao.findByUserName(userRequest.getUserName());
    	Boolean authorization = false;
    	    	
    	if(user.getPassword().equals(userRequest.getPassword())) { 
    		authorization = true;
    	}

    	return authorization;
    }     
}