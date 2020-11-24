package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Store;
import edu.escuelaing.arsw.model.User;
import edu.escuelaing.arsw.persistence.ProductPersistence;
import edu.escuelaing.arsw.persistence.StorePersistence;
import edu.escuelaing.arsw.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements edu.escuelaing.arsw.services.UserService {
    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    StorePersistence storePersistence;

    @Override
    public String login(String email, String password) throws UserServiceException {
        boolean login = false;
        try {
            Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("salt");
            User user = userPersistence.findByEmail(email);
//            user.setToken(token);
//            userPersistence.save(user);
            login = passwordEncoder.matches(password,user.getPassword());
            if (!login) {
                throw new UserServiceException("Incorrect password");
            }
            return getToken(user);
        } catch (NullPointerException e) {
            /*if (e.getMessage()==null) {
                login = false;
                return login;
            }*/
            throw new UserServiceException("Email doesn't found");
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String confirmPassword) throws UserServiceException {
    }

    @Override
    public void changeProfilePhoto(String image) throws UserServiceException {
    }

    @Override
    public void updateCash(int moneySpent) throws UserServiceException {
    }

    @Override
    public void register(User user) throws UserServiceException {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("salt");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //User user = new User(name,  lastName, email, password, rol, address, image, cash, cellphone);
        try{
            userPersistence.save(user);
            if (user.getRol().equals("MECA")) {
            	registerStore(user.getName()+" Store", user.getId());
            }
                      
        }catch (IllegalArgumentException e){
            throw new UserServiceException("The user couldn't be registred");
        }
    }

    @Override
    public List<User> findAll() throws UserServiceException {
        try {
            return (List<User>) userPersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException("There aren't Users");
        }
    }

    @Override
    public Optional<User> findById(long id) throws UserServiceException {
        try {
            return userPersistence.findById(id);
        } catch (Exception e) {
            throw new UserServiceException("User not found");
        }
    }

    @Override
    public User findByToken(String token) throws UserServiceException {
        try {
            return userPersistence.findByToken(token);
        } catch (Exception e) {
            throw new UserServiceException("Please login again");
        }
    }

    @Override
    public List<User> findByRol(String rol) throws UserServiceException {
        try {
            return userPersistence.findByRol(rol);
        } catch (Exception e) {
            throw new UserServiceException("Please login again");
        }
    }

    @Override
    public String getToken(User user) throws UserServiceException {
        String tokenU = user.getToken();
        String token;
        if(tokenU==null) {
//            String[] strings = tokenU.split("userid");
//            System.out.println(LocalDateTime.now().compareTo(LocalDateTime.parse(strings[2])));
//            System.out.println(strings[1].substring(1));
            token = UUID.randomUUID().toString().toUpperCase()
                    + "userid"
                    + LocalDateTime.now();
            user.setToken(token);
            userPersistence.save(user);
        } else {
            token = tokenU;
        }
        return token;
    }
    @Override
    public void registerStore(String store, long fk_mechanic) throws UserServiceException {
        Store store1 = new Store (store, fk_mechanic);
        try{
            storePersistence.save(store1);
        }catch (IllegalArgumentException e){
            throw new UserServiceException("The store couldn't be registred");
        }
    }


//    @Override
//    public void registerStore(String store, long fk_mechanic) throws UserServiceException {
//        Store store1 = new Store (store, fk_mechanic);
//        try{
//            storePersistence.save(store1);
//        }catch (IllegalArgumentException e){
//            throw new UserServiceException("The store couldn't be registred");
//        }
//    }
}
