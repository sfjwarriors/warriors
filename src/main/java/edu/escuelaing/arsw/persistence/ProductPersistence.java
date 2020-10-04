package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ProductPersistence extends CrudRepository<Product, Long> {
    public List<Product> findByStatusAndFkStoreProduct(String status, Long fkStoreProduct);
//    public List<Product> findByStatusAndPrice(String status, Long price);
}