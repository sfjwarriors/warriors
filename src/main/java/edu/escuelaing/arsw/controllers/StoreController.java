package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Store;
import edu.escuelaing.arsw.services.StoreService;
import edu.escuelaing.arsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stores")
public class StoreController {
    @Autowired
    public StoreService storeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllProducts() {
        try {
            List<Store> storeList = storeService.findAll();
            return new ResponseEntity<>(storeList, HttpStatus.ACCEPTED);
        } catch (StoreServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerProduct(@RequestBody Store store) {
        try {
            System.out.println(store.toString());
            storeService.registerStore(store.getStoreName(), store.getFkMechanic());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
