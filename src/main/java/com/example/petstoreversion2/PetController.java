package com.example.petstoreversion2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets")
public class PetController {

	@Autowired
	private PetService petService;

	@GetMapping
	public List<Pet> getAllPets() {
		return petService.getAllPets();
	}
	
	@PostMapping
	public Pet savePet(@RequestBody Pet pet) {
		return petService.savePet(pet);
	}
	
	@GetMapping("/{id}")
	public Optional<Pet> getPetById(@PathVariable Long id) {
		return petService.getPetById(id);
	}

	@PutMapping("/{id}")
	public Pet updatePet(@PathVariable Long id, @RequestBody Pet updatedPet) {
		return petService.updatePet(id, updatedPet);
	}
} 