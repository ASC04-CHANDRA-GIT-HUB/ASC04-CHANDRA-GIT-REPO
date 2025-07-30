package com.ecz.service;

import com.ecz.model.Product;

import java.util.List;

public interface ProductService {
    public boolean addProduct(Product product);
    public List<Product> getAllProducts();
    boolean updateProduct(Product product);
}