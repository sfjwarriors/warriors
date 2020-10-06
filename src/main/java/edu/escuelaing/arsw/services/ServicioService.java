package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.ServicioServiceException;
import edu.escuelaing.arsw.model.Servicio;


import java.util.List;

public interface ServicioService {
    public void register(String name, String image, String description, long price, String status, long fkStoreService) throws ServicioServiceException;
    public void updateService(Servicio service) throws ServicioServiceException;
    public void deleteService(long idService) throws ServicioServiceException;
//    public void orderService(long idService, long idUser) throws ServiceServiceException;
    public List<Servicio> findAll() throws ServicioServiceException;
    public List <Servicio> findByStatusAndFkStoreService(String status, Long fkStoreService) throws ServicioServiceException;
}
