package demo.repository;
 
import java.util.List;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import demo.model.Person;
 
@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {

}