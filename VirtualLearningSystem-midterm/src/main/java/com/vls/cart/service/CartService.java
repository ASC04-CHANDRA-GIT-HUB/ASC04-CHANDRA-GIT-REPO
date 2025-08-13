package com.vls.cart.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.vls.cart.model.CartModel;
import com.vls.cart.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public CartModel addCart(CartModel cart) {
        return repo.save(cart);
    }

    public List<CartModel> getAllCarts() {
        return repo.findAll();
    }

    public boolean deleteCart(int id) {
        return repo.deleteById(id);
    }

    public Optional<CartModel> getCartById(int id) {
        return repo.findById(id);
    }
}
