package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.*;
import edu.escuelaing.arsw.services.ServicioService;
import edu.escuelaing.arsw.services.UserService;
import edu.escuelaing.arsw.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ServicioService servicioService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.register(user);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mechanics")
    public ResponseEntity<?> findMechanics() {
        try {
            List<User> userList = userService.findByRol("MECA");
            for (User u : userList) {
                u.setPassword("");
                u.setToken("");
                u.setOrdens(null);
                u.setCash(0);
            }
            return new ResponseEntity<>(userList, HttpStatus.ACCEPTED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idUser}/orders")
    public ResponseEntity<?> findOrderUserId(@PathVariable Long idUser) {
        // System.out.println(idUser);
        try {
            Optional<User> user = userService.findById(idUser);
            if (user.isPresent()) {
                User u = user.get();
                List<Orden> ordens = u.getOrdens();
                List<OrdenReturn> ordenReturns = new ArrayList<>();
                for (Orden o : ordens) {
                    List<Cart> carts = o.getCarts();
                    List<Product> products = new ArrayList<>();
                    List<Servicio> servicios = new ArrayList<>();
                    for (Cart cart : carts) {
                        if (cart.getFkProductCart() != null && cart.getFkServicesCart() == null) {
                            products.add(productService.findById(cart.getFkProductCart()));
                        } else if (cart.getFkProductCart() == null && cart.getFkServicesCart() != null) {
                            servicios.add(servicioService.findById(cart.getFkServicesCart()));
                        }
                    }
                    ordenReturns.add(new OrdenReturn(o.getId(),o.getFkStore(), products, servicios, o.getTotalValue(), o.getStatusOrder()));
                }
                return new ResponseEntity<>(ordenReturns, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (UserServiceException | ProductServiceException | ServicioServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
