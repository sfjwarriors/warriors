package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.UserPersistenceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.User;
import edu.escuelaing.arsw.persistence.UserPersistence;
import edu.escuelaing.arsw.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserServices {
    @Autowired
    private UserPersistence userPersistence;

    @Override
    public void login(String email, String password) throws UserServiceException {
        boolean login = false;
        try {
            Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("salt");
            System.out.println("hello");
            User user = userPersistence.findByEmail(email);
            System.out.println(user.toString());
            login = passwordEncoder.matches(password,user.getPassword());
            System.out.println(login);
            if (!login) {
                throw new UserServiceException("The passwords doesn't match");
            }
        } catch (UserPersistenceException e) {
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
    public void register(String name, String lastName, String email, String password, String rol, String address, String image, long cash) throws UserServiceException {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("salt");
        password = passwordEncoder.encode(password);
        System.out.println(password);
        User user = new User(name,  lastName, email, password, rol, address, image, cash);
        try{
            userPersistence.save(user);
        }catch (IllegalArgumentException e){
            throw new UserServiceException("The user couldn't be registred");
        }
    }

    @Override
    public List<User> findAll() throws UserServiceException {
        return null;
    }

    @Override
    public Optional<User> findById(long id) throws UserServiceException {
        return Optional.empty();
    }
}
