package com.ecz.service;

import com.ecz.model.Product;
import com.ecz.repository.ProductRepository;
import com.ecz.repository.ProductRepositoryCollectionImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    //What is a service?
    //A service is a class that provides business logic to the application.
    ProductRepository productRepository = new ProductRepositoryCollectionImpl();
    public boolean addProduct(Product product) {
        boolean isAdded = false;
        if (product.getPrice() < 0) {
            System.out.println("Price cannot be negative");
        } else {
            isAdded =  productRepository.addProductToCart(product);
        }
        return isAdded;
    }
    // get all products
    public List<Product> getAllProducts() {

        return productRepository.getAllProducts();
    }

    @Override
    public boolean updateProduct(Product product) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}