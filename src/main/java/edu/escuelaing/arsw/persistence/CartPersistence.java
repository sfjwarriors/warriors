package edu.escuelaing.arsw.persistence;

import edu.escuelaing.arsw.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartPersistence extends CrudRepository<Cart, Long> {
//    public Cart findByIdAndFkProductCart(String status, Long fkStoreProduct);
}
