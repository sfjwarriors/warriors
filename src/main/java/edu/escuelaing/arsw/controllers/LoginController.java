package edu.escuelaing.arsw.controllers;

import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.User;
import edu.escuelaing.arsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;


@RestController
@RequestMapping(value = "login")
public class LoginController {
    @Autowired
    public UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = userService.login(user.getEmail(), user.getPassword());
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> getByToken(@PathVariable String token) {
        try {
            User user = userService.findByToken(token);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }}
