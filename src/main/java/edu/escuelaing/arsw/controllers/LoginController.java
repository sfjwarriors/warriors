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

//Token
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;

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
//            System.out.println(token);
            User user = userService.findByToken(token);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (UserServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    /*private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }*/

}
