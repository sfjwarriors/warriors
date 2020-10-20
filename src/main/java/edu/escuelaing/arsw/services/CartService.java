package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.model.Cart;
import edu.escuelaing.arsw.model.Orden;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    public void addToCart(Cart cart) throws OrderServiceException;
    public void deleteFromCart(Cart cart) throws OrderServiceException;
    public List<Cart> findAll() throws OrderServiceException;
    public boolean existCartById(long id) throws OrderServiceException;
//    public Cart findByCartId(long id) throws OrderServiceException;
//    public Cart findByTypeId(long id) throws OrderServiceException;
}
