package com.vls.cart.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vls.cart.model.CartModel;
import com.vls.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CartModel> addCart(@RequestBody CartModel cart) {
        return ResponseEntity.ok(service.addCart(cart));
    }

    @GetMapping
    public ResponseEntity<List<CartModel>> getAllCarts() {
        return ResponseEntity.ok(service.getAllCarts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable int id) {
        boolean deleted = service.deleteCart(id);
        return deleted ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartModel> getCartById(@PathVariable int id) {
        Optional<CartModel> cart = service.getCartById(id);
        return cart.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
