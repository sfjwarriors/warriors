package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class Controller {
    @Autowired
    public UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas() {
        String s = "";
        try {
            //userService.register("santiago", "lopez", "santiago@mail.com", "1234", "USER", "Carrera 123", "img", 10000000);
            userService.login("santi@mail.com", "1234");
            s+="funciono";
        } catch (UserServiceException e) {
            s+="no funciono";
            e.printStackTrace();
        }
        return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
    }

}