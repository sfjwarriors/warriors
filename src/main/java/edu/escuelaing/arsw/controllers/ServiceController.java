package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.model.Servicio;
import edu.escuelaing.arsw.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ServiceController {
    @Autowired
    public ServicioService servicioService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAllServices() {
        try {
            List<Servicio> servicios = servicioService.findAll();
            return new ResponseEntity<>(servicios, HttpStatus.ACCEPTED);
        } catch (ServicioServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{status}/{fkStoreService}", method = RequestMethod.GET)
    public ResponseEntity<?> findServicesByStatus(@PathVariable String status, @PathVariable Long fkStoreService) {
//        System.out.println(status + fkStoreService + "findProductsByStatus");
        try {
            List<Servicio> servicioList = servicioService.findByStatusAndFkStoreService(status, fkStoreService);
            return new ResponseEntity<>(servicioList, HttpStatus.ACCEPTED);
        } catch (ServicioServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerService(@RequestBody Servicio servicio) {
        try {
            servicioService.register(servicio.getName(), servicio.getImage(), servicio.getDescription(), servicio.getPrice(), servicio.getStatus(), servicio.getFkStoreService());
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ServicioServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateService(@RequestBody Servicio servicio) {
        try {
            servicioService.updateService(servicio);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (ServicioServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
