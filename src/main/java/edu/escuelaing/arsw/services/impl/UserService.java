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

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements edu.escuelaing.arsw.services.UserService {
    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private StorePersistence storePersistence;

    @Override
    public boolean login(String email, String password) throws UserServiceException {
        boolean login = false;
        try {
            Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("salt");
            User user = userPersistence.findByEmail(email);
            login = passwordEncoder.matches(password,user.getPassword());
            if (!login) {
                throw new UserServiceException("Incorrect password");
            }
            return login;
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
    public void register(String name, String lastName, String email, String password, String rol, String address, String image, long cash, long cellphone) throws UserServiceException {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("salt");
        password = passwordEncoder.encode(password);
        User user = new User(name,  lastName, email, password, rol, address, image, cash, cellphone);
        try{
            userPersistence.save(user);
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
        return Optional.empty();
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
}
