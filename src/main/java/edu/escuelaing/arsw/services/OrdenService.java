package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.OrderServiceException;
import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.model.Orden;
import edu.escuelaing.arsw.model.Product;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrdenService {
    public Long createOrden(Orden orden) throws OrderServiceException;
    public void updateOrden(Orden orden) throws OrderServiceException;
    public void deleteOrden(Orden orden) throws OrderServiceException;
    public List<Orden> findAll() throws OrderServiceException;
    public Orden findById(long id) throws OrderServiceException;
}
