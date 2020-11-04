package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.model.Product;
import edu.escuelaing.arsw.persistence.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements edu.escuelaing.arsw.services.ProductService {

    @Autowired
    private ProductPersistence productPersistence;

    @Override
    public void register(Product product) throws ProductServiceException {
        try {
            productPersistence.save(product);
        } catch (IllegalArgumentException e) {
            throw new ProductServiceException("The product couldn't be registred");
        }
    }

    @Override
    public void updateProduct(Product product) throws ProductServiceException {
        try {
            productPersistence.save(product);
        } catch (NullPointerException e) {
            throw new ProductServiceException("The product couldn't be update");
        }
    }

    @Override
    public void deleteProduct(long idProduct) throws ProductServiceException {
        try {
            //productPersistence.deleteById(idProduct);
            Optional<Product> present = productPersistence.findById(idProduct);
            if(present.isPresent()){
                Product product = present.get();
                product.setStatus("unavailable");
                productPersistence.save(product);
            } else {
                throw new ProductServiceException("The product couldn't be deleted or doesn't exist");
            }
        } catch (Exception e) {
            throw new ProductServiceException("The product couldn't be deleted or doesn't exist");
        }
    }


//    @Override
//    public void orderProduct(long idProduct, long idUser) {
//
//    }

    @Override
    public List<Product> findAll() throws ProductServiceException {
        try {
            return (List<Product>) productPersistence.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProductServiceException("There aren't Products");
        }
    }

    @Override
    public List<Product> findByStatusAndFkStoreProduct(String status, Long fkStoreProduct) throws ProductServiceException {
        try {
            return productPersistence.findByStatusAndFkStoreProduct(status, fkStoreProduct);
        } catch (NullPointerException e) {
            throw new ProductServiceException("Product doesn't found");
        }
    }

    @Override
    public Product findById(long id) throws ProductServiceException {
        Optional<Product> product = productPersistence.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductServiceException("The product can't be found");
        }
    }
}
