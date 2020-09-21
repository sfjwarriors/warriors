package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.Exceptions.UserPersistenceException;
import edu.escuelaing.arsw.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface UserPersistence extends CrudRepository<User, Integer>{
    public User findByEmail(String email);
}