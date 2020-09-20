package edu.escuelaing.arsw.services.impl;

import edu.escuelaing.arsw.Exceptions.ProductServiceException;
import edu.escuelaing.arsw.persistence.ProductPersistence;
import edu.escuelaing.arsw.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductPersistence productPersistence;

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
}
