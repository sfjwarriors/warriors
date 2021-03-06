package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.model.Cart;
import edu.escuelaing.arsw.model.Orden;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrdenController {
    @Autowired
    OrdenService ordenService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerOrder(@RequestBody Orden orden) {
        try {
            Long n = ordenService.createOrden(orden);
            return new ResponseEntity<>(n, HttpStatus.CREATED);
        } catch (OrderServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllOrders() {
        try {
            List<Orden> ordenList = ordenService.findAll();
            return new ResponseEntity<>(ordenList, HttpStatus.ACCEPTED);
        } catch (OrderServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
