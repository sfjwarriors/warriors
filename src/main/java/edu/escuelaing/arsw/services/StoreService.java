package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    public List<Store> findAll() throws StoreServiceException;
    public Store findByIdMechanic(long idMechanic) throws StoreServiceException;
    public Store findById(long idStore) throws StoreServiceException;
    public void updateStore(Store store) throws StoreServiceException;
    public void registerStore(Store store) throws UserServiceException;
}
