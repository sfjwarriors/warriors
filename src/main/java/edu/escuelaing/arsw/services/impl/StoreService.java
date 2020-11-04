package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Orden;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Store;
import edu.escuelaing.arsw.model.User;
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

    @Override
    public Store findByIdMechanic(long idMechanic) throws StoreServiceException {
        try {
            return storePersistence.findByFkMechanic(idMechanic);
        } catch (Exception e) {
            e.printStackTrace();
            throw new StoreServiceException("The mechanic "+idMechanic+" don't have a store");
        }
    }

    @Override
    public void updateStore(Store store) throws StoreServiceException {
        try{
//            System.out.println(store);
//            System.out.println(store.getOrdens().get(0).getCarts());
//            storePersistence.save(store);
            Store storeTmp = storePersistence.findById(store.getId()).get();
            storeTmp.setStoreName(store.getStoreName());
            storePersistence.save(storeTmp);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new StoreServiceException("The store name couldn't be update");
        }
    }

	@Override
    public void registerStore(Store store) throws UserServiceException {
        try{
            storePersistence.save(store);
        }catch (IllegalArgumentException e){
            throw new UserServiceException("The store couldn't be registred");
        }
    }
}
