package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.model.Store;

import java.util.List;

public interface StoreService {
    public List<Store> findAll() throws StoreServiceException;
}
