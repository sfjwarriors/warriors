package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.User;

import java.util.List;

public interface ProductService {
    public void register(String name, String description, long price, String image, String status, long fk_mechanic_product) throws ProductServiceException;
    public void updateProductStatus(long idProduct, String status) throws ProductServiceException;
    public void updateProductName(long idProduct, String name) throws ProductServiceException;
    public void updateProductDescription(long idProduct, String description) throws ProductServiceException;
    public void updateProductPrice(long idProduct, long price) throws ProductServiceException;
    public void deleteProduct(long idProduct) throws ProductServiceException;
    public void orderProducct(long idProduct, long idUser) throws ProductServiceException;
    public Iterable<Product> findAll() throws ProductServiceException;
}
