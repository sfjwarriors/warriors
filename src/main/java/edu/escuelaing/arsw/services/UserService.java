package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public String login(String email, String password) throws UserServiceException;
    public void changePassword(String oldPassword, String newPassword, String confirmPassword) throws UserServiceException;
    public void changeProfilePhoto(String image) throws UserServiceException;
    public void updateCash(int moneySpent) throws UserServiceException;
    public void register(String name, String lastName, String email, String password, String rol, String address, String image, long cash, long cellphone) throws UserServiceException;
    public List<User> findAll() throws UserServiceException;
    public Optional<User> findById(long id) throws UserServiceException;
    public User findByToken(String token) throws UserServiceException;
    public String getToken(User user) throws UserServiceException;

//    public void registerStore(String store, long fk_mechanic) throws  UserServiceException;
}
