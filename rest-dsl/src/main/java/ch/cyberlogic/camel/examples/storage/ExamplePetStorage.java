package ch.cyberlogic.camel.examples.storage;

import ch.cyberlogic.camel.examples.entity.Pet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ExamplePetStorage {

    private final ConcurrentHashMap<Long, Pet> pets = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public void createPet(Pet pet) {
        pet.setId(idCounter.getAndIncrement());
        pets.put(pet.getId(), pet);
    }

    public Pet getPet(long id) {
        return pets.get(id);
    }

    public void updatePet(Pet pet) {
        pets.put(pet.getId(), pet);
    }

    public void deletePet(long id) {
        pets.remove(id);
    }

    public List<Pet> getAllPets() {
        return new ArrayList<>(pets.values());
    }

}
