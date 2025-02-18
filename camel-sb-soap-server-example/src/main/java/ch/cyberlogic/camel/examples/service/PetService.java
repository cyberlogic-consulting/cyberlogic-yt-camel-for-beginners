package ch.cyberlogic.camel.examples.service;

import ch.cyberlogic.camel.examples.model.Pet;
import ch.cyberlogic.camel.examples.model.cxf.GetPetsResponse;
import ch.cyberlogic.camel.examples.model.cxf.NoSuchPetException;

public interface PetService {

	void addPet(Pet pet);

	Pet getPet(Long id) throws NoSuchPetException;

	GetPetsResponse getPets();

	void updatePet(Long id, Pet pet) throws NoSuchPetException;

	void removePet(Long id) throws NoSuchPetException;
}
