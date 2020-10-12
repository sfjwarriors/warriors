package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Store;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface StorePersistence extends CrudRepository<Store, Long> {
    public Store findByFkMechanic(long idMechanic);
}
