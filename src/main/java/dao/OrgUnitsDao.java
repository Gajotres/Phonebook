package dao;

import model.OrgUnit;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface OrgUnitsDao extends CrudRepository<OrgUnit, Long> {
  
  public Iterable<OrgUnit> findAll();
    
}