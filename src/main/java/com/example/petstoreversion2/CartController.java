package com.example.petstoreversion2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
    private CartService cartService;

    @PostMapping("/add/{petId}")
    public ResponseEntity<Map<String, Object>> addToCart(@PathVariable Long petId) {
        Cart cart = cartService.addPetToCart(1L, petId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Pet added to Cart");
        response.put("cartId", cart.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/remove/{petId}")
    public ResponseEntity<Map<String, Object>> removeFromCart(@PathVariable Long petId) {
        Cart cart = cartService.removePetFromCart(1L, petId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Pet removed from Cart");
        response.put("cartId", cart.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartItems(@PathVariable Long id) {
        Optional<Cart> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getCartProducts() {
        Optional<Cart> optionalCart = cartService.getCartById(1L); // Assuming cart ID is 1
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Set<Pet> pets = cart.getPets();
            
            Map<String, Object> response = new HashMap<>();
            response.put("pets", pets);
            response.put("message", "Pets fetched successfully from the cart");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("message", "Cart not found"));
        }
    }
}
