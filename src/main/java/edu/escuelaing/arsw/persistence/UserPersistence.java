package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface UserPersistence extends CrudRepository<User, Long>{
    public User findByEmail(String email);
}