package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


@Repository
public interface UserPersistence extends CrudRepository<User, Long>{
    public User findByEmail(String email);
    public List<User> findByRol(String rol);
    public User findByToken(String token);
}