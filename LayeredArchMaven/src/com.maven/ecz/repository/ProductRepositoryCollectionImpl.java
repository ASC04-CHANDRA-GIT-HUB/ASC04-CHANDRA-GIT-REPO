package com.ecz.repository;

import com.ecz.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryCollectionImpl implements ProductRepository {
    //What is a repository?
    //A repository is a class that provides CRUD operations on the model.
    Map<String, Product> cart = new HashMap<String, Product>();

    public boolean addProductToCart(Product product) {
        cart.put(product.getId(), product);
//        System.out.println("Product added to cart " );
        System.out.println("Updated cart is " + cart);
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        // throw new UnsupportedOperationException("Not implemented yet");
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("1", "Product 1", 100, 10));
        products.add(new Product("2", "Product 2", 1001, 20));
        products.add(new Product("3", "Product 3", 1002, 30));
        products.add(new Product("4", "Product 4", 1003, 40));
        products.add(new Product("5", "Product 5", 1004, 50));
        return products;
    }
}