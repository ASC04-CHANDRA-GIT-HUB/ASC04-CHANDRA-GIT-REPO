package com.vls.cart.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.vls.cart.model.CartModel;

@Repository
public class CartRepository {

    private List<CartModel> carts = new ArrayList<>();
    private int nextId = 1;

    public CartModel save(CartModel cart) {
        cart.setCartId(nextId++);
        carts.add(cart);
        return cart;
    }

    public List<CartModel> findAll() {
        return carts;
    }

    public boolean deleteById(int id) {
        return carts.removeIf(c -> c.getCartId() == id);
    }

    public Optional<CartModel> findById(int id) {
        return carts.stream().filter(c -> c.getCartId() == id).findFirst();
    }
}
