package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.model.Cart;
import edu.escuelaing.arsw.persistence.CartPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements edu.escuelaing.arsw.services.CartService {
    @Autowired
    CartPersistence cartPersistence;

    @Override
    public void addToCart(Long fkProductCart, Long fkOrderCart, Long fkServicesCart) throws OrderServiceException {
        Cart cart = new Cart(fkProductCart, fkOrderCart, fkServicesCart);
        try {
            cartPersistence.save(cart);
        } catch (Exception e) {
            throw new OrderServiceException("The product can't be added to the cart");
        }
    }

    @Override
    public void deleteFromCart(Cart cart) throws OrderServiceException {
        try {
            cartPersistence.delete(cart);
        } catch (Exception e) {
            throw new OrderServiceException("The product can't be removed from the cart");
        }
    }

    @Override
    public List<Cart> findAll() throws OrderServiceException {
        try {
            return (List<Cart>) cartPersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderServiceException("There aren't Products");
        }
    }

    @Override
    public boolean existCartById(long id) throws OrderServiceException {
        Optional<Cart> cart = cartPersistence.findById(id);
        if(cart.isPresent()){
            return true;
        } else {
            throw new OrderServiceException("The cart can't be found");
        }
    }
}
