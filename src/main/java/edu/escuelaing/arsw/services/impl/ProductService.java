package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.Exceptions.UserServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.persistence.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements edu.escuelaing.arsw.services.ProductService {

    @Autowired
    private ProductPersistence productPersistence;

    @Override
    public void register(String name, String description, long price, String image, String status, long fk_store_product) throws ProductServiceException {
        Product product = new Product(name, description, price, image, status, fk_store_product);
        try{
            productPersistence.save(product);
        }catch (IllegalArgumentException e){
            throw new ProductServiceException("The product couldn't be registred");
        }
    }

    @Override
    public void updateProductStatus(long idProduct, String status) throws ProductServiceException {

    }

    @Override
    public void updateProductName(long idProduct, String name) throws ProductServiceException {

    }

    @Override
    public void updateProductDescription(long idProduct, String description) throws ProductServiceException {

    }

    @Override
    public void updateProductPrice(long idProduct, long price) throws ProductServiceException {

    }

    @Override
    public void deleteProduct(long idProduct) throws ProductServiceException {

    }

    @Override
    public void orderProducct(long idProduct, long idUser) {

    }

    @Override
    public Iterable<Product> findAll() throws ProductServiceException {
        try {
            return productPersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProductServiceException("There aren't Products");
        }
    }
}
