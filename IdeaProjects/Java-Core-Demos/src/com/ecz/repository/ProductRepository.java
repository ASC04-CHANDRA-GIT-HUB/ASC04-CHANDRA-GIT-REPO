package com.ecz.repository;

import com.ecz.model.Product;

import java.util.List;

public interface ProductRepository {
    boolean addProductToCart(Product product);
    List<Product> getAllProducts();
}