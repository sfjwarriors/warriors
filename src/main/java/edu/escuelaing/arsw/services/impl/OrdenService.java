package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.model.Cart;
import edu.escuelaing.arsw.model.Orden;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.persistence.OrdenPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements edu.escuelaing.arsw.services.OrdenService {
    @Autowired
    OrdenPersistence ordenPersistence;

    @Override
    public void createOrden(Orden orden) throws OrderServiceException {
        try{
            ordenPersistence.save(orden);
        }catch (Exception e) {
            e.printStackTrace();
            throw new OrderServiceException("The order couldn't be registred");
        }

    }

    @Override
    public void updateOrden(Orden orden) throws OrderServiceException {

    }

    @Override
    public void deleteOrden(Orden orden) throws OrderServiceException {

    }

    @Override
    public List<Orden> findAll() throws OrderServiceException {
        try {
            return (List<Orden>) ordenPersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderServiceException("There aren't Orders");
        }
    }

    @Override
    public Orden findById(long id) throws OrderServiceException {
        Optional<Orden> orden = ordenPersistence.findById(id);
        if (orden.isPresent()) {
            return orden.get();
        } else {
            throw new OrderServiceException("The order can't be found");
        }
    }
}
