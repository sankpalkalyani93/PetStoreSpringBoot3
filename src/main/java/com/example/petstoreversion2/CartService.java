package com.example.petstoreversion2;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private PetRepository petRepository;

    public Cart addPetToCart(Long cartId, Long petId) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart());
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));

        if (cart.getPets().contains(pet)) {
            throw new RuntimeException("Pet already in cart");
        }

        cart.addPet(pet);
        return cartRepository.save(cart);
    }

    public Cart removePetFromCart(Long cartId, Long petId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));

        cart.removePet(pet);
        return cartRepository.save(cart);
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }
}
