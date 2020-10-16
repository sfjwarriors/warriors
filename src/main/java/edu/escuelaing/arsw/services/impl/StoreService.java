package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.StoreServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
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
            storePersistence.save(store);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new StoreServiceException("The store name couldn't be update");
        }
    }

	@Override
    public void registerStore(String store, long fk_mechanic) throws UserServiceException {
    	System.out.println(store + "....." + fk_mechanic);
        Store store1 = new Store (store, fk_mechanic);
        try{
            storePersistence.save(store1);
        }catch (IllegalArgumentException e){
            throw new UserServiceException("The store couldn't be registred");
        }
    }
}
