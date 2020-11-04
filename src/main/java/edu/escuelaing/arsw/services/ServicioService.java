package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.Servicio;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface ServicioService {
    public void register(Servicio servicio) throws ServicioServiceException;
    public void updateService(Servicio service) throws ServicioServiceException;
    public void deleteService(long idService) throws ServicioServiceException;
//    public void orderService(long idService, long idUser) throws ServiceServiceException;
    public List<Servicio> findAll() throws ServicioServiceException;
    public List <Servicio> findByStatusAndFkStoreService(String status, Long fkStoreService) throws ServicioServiceException;
    public Servicio findById(long id) throws ServicioServiceException;
}
