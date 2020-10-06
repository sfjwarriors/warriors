package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Store;
import edu.escuelaing.arsw.persistence.StorePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements edu.escuelaing.arsw.services.StoreService {
    @Autowired
    StorePersistence storePersistence;

    @Override
    public List<Store> findAll() throws StoreServiceException {
        try {
            return (List<Store>) storePersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new StoreServiceException("There aren't Stores");
        }

    }
}
