package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.*;
import edu.escuelaing.arsw.model.Cart;
import edu.escuelaing.arsw.model.Orden;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Store;
import edu.escuelaing.arsw.services.CartService;
import edu.escuelaing.arsw.services.OrdenService;
import edu.escuelaing.arsw.services.StoreService;
import edu.escuelaing.arsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, value = "/id/{idStore}")
    public ResponseEntity<?> findStoreById(@PathVariable Long idStore) {
        try {
            Store store = storeService.findById(idStore);
            return new ResponseEntity<>(store, HttpStatus.ACCEPTED);
        } catch (StoreServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{idMechanic}", method = RequestMethod.GET)
    public ResponseEntity<?> getStoreByIdMechanic(@PathVariable long idMechanic) {
        try {
            Store store = storeService.findByIdMechanic(idMechanic);
            return new ResponseEntity<>(store, HttpStatus.ACCEPTED);
        } catch (StoreServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerStore(@RequestBody Store store) {
        try {
            storeService.registerStore(store);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateStore(@RequestBody Store store) {
        try {
            storeService.updateStore(store);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (StoreServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
