package dao;

import model.User;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
  
  public User findById(int id);
  
  public User findByUserName(String userName);  
}