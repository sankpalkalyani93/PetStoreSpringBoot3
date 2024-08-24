package com.example.petstoreversion2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;
	
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}
	
	public Pet savePet(Pet pet) {
		return petRepository.save(pet);
	}
	
	// method to fetch a pet by id
	public Optional<Pet> getPetById(Long id) {
		return petRepository.findById(id);
	}
	
	public Pet updatePet(Long id, Pet updatedPet) {
		return petRepository.findById(id).map(
				pet -> {
					pet.setName(updatedPet.getName());
					pet.setAge(updatedPet.getAge());
					pet.setDescription(updatedPet.getDescription());
					pet.setPrice(updatedPet.getPrice());
					return petRepository.save(pet);
				}).orElse(null);
	}
}
