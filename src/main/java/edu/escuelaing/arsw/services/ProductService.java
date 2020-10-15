package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public void register(String name, String description, long price, String image, String status, long fkStoreProduct) throws ProductServiceException;
    public void updateProduct(Product product) throws ProductServiceException;
    public void deleteProduct(long idProduct) throws ProductServiceException;
//    public void orderProduct(long idProduct, long idUser) throws ProductServiceException;
    public List<Product> findAll() throws ProductServiceException;
    public List <Product> findByStatusAndFkStoreProduct(String status, Long fkStoreProduct) throws ProductServiceException;
    public Product findById(long id) throws ProductServiceException;
}
