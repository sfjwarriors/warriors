package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.model.Cart;
import edu.escuelaing.arsw.model.Orden;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Servicio;
import edu.escuelaing.arsw.services.CartService;
import edu.escuelaing.arsw.services.OrdenService;
import edu.escuelaing.arsw.services.ProductService;
import edu.escuelaing.arsw.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/carts")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    ServicioService servicioService;

    @Autowired
    OrdenService ordenService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllCarts() {
        try {
            List<Cart> cartList = cartService.findAll();
            return new ResponseEntity<>(cartList, HttpStatus.ACCEPTED);
        } catch (OrderServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addToTheCart(@RequestBody Cart cart) {
        //System.out.println(cart.toString());
        try {
            Orden orden = ordenService.findById(cart.getFkOrderCart());
            if(cart.getFkProductCart()!=null && cart.getFkServicesCart()==null && cart.getFkOrderCart()!=null){
                Product product = productService.findById(cart.getFkProductCart());
//                Long temp = orden.getTotalValue();
//                Long temp1 = product.getPrice();
//                System.out.println(temp);
//                System.out.println(temp1);
//                orden.setTotalValue(temp+temp1);
                cartService.addToCart(cart);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            } else if(cart.getFkProductCart()==null && cart.getFkServicesCart()!=null && cart.getFkOrderCart()!=null){
                Servicio servicio = servicioService.findById(cart.getFkServicesCart());
//                Long temp = orden.getTotalValue();
//                Long temp1 = servicio.getPrice();
//                System.out.println(temp);
//                System.out.println(temp1);
//                orden.setTotalValue(temp+temp1);
                cartService.addToCart(cart);
                return new ResponseEntity<>("Success", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("You don't add any product or service", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (OrderServiceException | ProductServiceException | ServicioServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFromTheCart(@RequestBody Cart cart) {
        try {
            if(cartService.existCartById(cart.getId())) {
                Orden orden = ordenService.findById(cart.getFkOrderCart());
                if (cart.getFkProductCart() != null && cart.getFkServicesCart() == null && cart.getFkOrderCart() != null && cart.getId() != null) {
                    Product product = productService.findById(cart.getFkProductCart());
                    orden.setTotalValue(orden.getTotalValue() - product.getPrice());
                    cartService.deleteFromCart(cart);
                    return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
                } else if (cart.getFkProductCart() == null && cart.getFkServicesCart() != null && cart.getFkOrderCart() != null && cart.getId() != null) {
                    Servicio servicio = servicioService.findById(cart.getFkServicesCart());
                    orden.setTotalValue(orden.getTotalValue() - servicio.getPrice());
                    cartService.deleteFromCart(cart);
                    return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity<>("You don't delete any product or service", HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                return new ResponseEntity<>("You don't delete any product or service", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (OrderServiceException | ProductServiceException | ServicioServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
