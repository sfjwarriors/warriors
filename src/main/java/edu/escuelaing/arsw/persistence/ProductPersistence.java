package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductPersistence extends CrudRepository<Product, Long> {

}