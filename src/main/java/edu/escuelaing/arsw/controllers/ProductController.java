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
    public UserService userService;

    @Autowired
    public ServicioService servicioService;

    @Autowired
    public ProductService productService;

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            userService.login(user.getEmail(), user.getPassword());
//            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
//        } catch (UserServiceException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        try {
//            userService.register(user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRol(), user.getAddress(), user.getImage(), 0, user.getCellphone());
//            return new ResponseEntity<>("Success", HttpStatus.CREATED);
//        } catch (UserServiceException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllProducts() {
        try {
            List<Product> productList = productService.findAll();
            return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
        } catch (ProductServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @RequestMapping(value = "findservices", method = RequestMethod.GET)
//    public ResponseEntity<?> findAllServices() {
//        try {
//            List<Servicio> servicios = servicioService.findAll();
//            return new ResponseEntity<>(servicios, HttpStatus.ACCEPTED);
//        } catch (ServicioServiceException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @RequestMapping(value = "/{status}/{fkStoreProduct}", method = RequestMethod.GET)
    public ResponseEntity<?> findProductsByStatus(@PathVariable String status, @PathVariable Long fkStoreProduct) {
//        System.out.println(status + fkStoreProduct + "findProductsByStatus");
        try {
            List<Product> productList = productService.findByStatusAndFkStoreProduct(status, fkStoreProduct);
            return new ResponseEntity<>(productList, HttpStatus.ACCEPTED);
        } catch (ProductServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//    @RequestMapping(value = "findservicesbyStatus/{status}/{fkStoreService}", method = RequestMethod.GET)
//    public ResponseEntity<?> findServicesByStatus(@PathVariable String status, @PathVariable Long fkStoreService) {
//        System.out.println(status + fkStoreService + "findProductsByStatus");
//        try {
//            List<Servicio> servicioList = servicioService.findByStatusAndFkStoreService(status, fkStoreService);
//            return new ResponseEntity<>(servicioList, HttpStatus.ACCEPTED);
//        } catch (ServicioServiceException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerProduct(@RequestBody Product product) {
        try {
            productService.register(product.getName(), product.getDescription(), product.getPrice(), product.getImage(), product.getStatus(), product.getFkStoreProduct());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<?> registerService(@RequestBody Servicio servicio) {
//        try {
//            servicioService.register(servicio.getName(), servicio.getImage(), servicio.getDescription(), servicio.getPrice(), servicio.getStatus(), servicio.getFkStoreService());
//            return new ResponseEntity<>("Success", HttpStatus.CREATED);
//        } catch (ServicioServiceException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

//    @RequestMapping(value = "registerstore", method = RequestMethod.POST)
//    public ResponseEntity<?> registerProduct(@RequestBody Store store) {
//        try {
//            userService.registerStore(store.getStoreName(), store.getFkMechanic());
//            return new ResponseEntity<>("Success", HttpStatus.CREATED);
//        } catch (UserServiceException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ProductServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

//    @RequestMapping(value = "updateservice", method = RequestMethod.POST)
//    public ResponseEntity<?> updateService(@RequestBody Servicio servicio) {
//        try {
//            servicioService.updateService(servicio);
//            return new ResponseEntity<>("Success", HttpStatus.CREATED);
//        } catch (ServicioServiceException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
//    }


}