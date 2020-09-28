package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.User;
import edu.escuelaing.arsw.services.ProductService;
import edu.escuelaing.arsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class Controller {
    @Autowired
    public UserService userService;

    @Autowired
    public ProductService productService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            userService.login(user.getEmail(), user.getPassword());
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.register(user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRol(), user.getAddress(), user.getImage(), user.getCash());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "findproducts", method = RequestMethod.GET)
    public ResponseEntity<?> findAllProducts() {
        try {
            Iterable<Product> productList = productService.findAll();
            for (Product p: productList) {
                System.out.println(p.toString());
            }
            return new ResponseEntity<>(productList, HttpStatus.FOUND);
        } catch (ProductServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "registerproduct", method = RequestMethod.POST)
    public ResponseEntity<?> registerProduct(@RequestBody Product product) {
        try {
            productService.register(product.getName(), product.getDescription(), product.getPrice(), product.getImage());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}