package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Servicio;
import edu.escuelaing.arsw.model.Store;
import edu.escuelaing.arsw.model.User;
import edu.escuelaing.arsw.services.ProductService;
import edu.escuelaing.arsw.services.ServicioService;
import edu.escuelaing.arsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    public ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllProducts() {
        try {
            List<Product> productList = productService.findAll();
            return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{status}/{fkStoreProduct}", method = RequestMethod.GET)
    public ResponseEntity<?> findProductsByStatus(@PathVariable String status, @PathVariable Long fkStoreProduct) {
        try {
            List<Product> productList = productService.findByStatusAndFkStoreProduct(status, fkStoreProduct);
            return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerProduct(@RequestBody Product product) {
        try {
            productService.register(product.getName(), product.getDescription(), product.getPrice(), product.getImage(), product.getStatus(), product.getFkStoreProduct());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "{idProduct}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable long idProduct) {
        try {
            productService.deleteProduct(idProduct);
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "{idProduct}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable long idProduct) {
        try {
            Product product = productService.findById(idProduct);
            return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}