package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Servicio;
import edu.escuelaing.arsw.persistence.ServicioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService implements edu.escuelaing.arsw.services.ServicioService {

    @Autowired
    private ServicioPersistence servicioPersistence;

    @Override
    public void register(Servicio servicio) throws ServicioServiceException {
        try {
            servicioPersistence.save(servicio);
        } catch (IllegalArgumentException e) {
            throw new ServicioServiceException("The servcice couldn't be registred");
        }
    }

    @Override
    public void updateService(Servicio service) throws ServicioServiceException {
        try {
            servicioPersistence.save(service);
        } catch (NullPointerException e) {
            throw new ServicioServiceException("The service couldn't be update");
        }
    }

    @Override
    public void deleteService(long idService) throws ServicioServiceException {
        try {
            servicioPersistence.deleteById(idService);
        } catch (Exception e) {
            throw new ServicioServiceException("The service couldn't be deleted or doesn't exist");
        }
    }

    @Override
    public List<Servicio> findAll() throws ServicioServiceException {
        try {
            return (List<Servicio>) servicioPersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServicioServiceException("There aren't Services");
        }
    }

    @Override
    public List<Servicio> findByStatusAndFkStoreService(String status, Long fkStoreService) throws ServicioServiceException {
        try {
            return servicioPersistence.findByStatusAndFkStoreService(status, fkStoreService);
        } catch (NullPointerException e) {
            throw new ServicioServiceException("Service doesn't found");
        }
    }

    @Override
    public Servicio findById(long id) throws ServicioServiceException {
        Optional<Servicio> servicio = servicioPersistence.findById(id);
        if(servicio.isPresent()) {
            return servicio.get();
        } else {
            throw new ServicioServiceException("The service can't be found");
        }
    }
}
