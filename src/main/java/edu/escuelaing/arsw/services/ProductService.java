package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;

public interface ProductService {
    public void updateProductStatus(long idProduct, String status) throws ProductServiceException;
    public void updateProductName(long idProduct, String name) throws ProductServiceException;
    public void updateProductDescription(long idProduct, String description) throws ProductServiceException;
    public void updateProductPrice(long idProduct, long price) throws ProductServiceException;
    public void deleteProduct(long idProduct) throws ProductServiceException;
    public void orderProducct(long idProduct, long idUser);
}
