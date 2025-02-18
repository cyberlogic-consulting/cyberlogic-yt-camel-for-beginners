package ch.cyberlogic.camel.examples.service.java2wsdl;

import ch.cyberlogic.camel.examples.petservice.Pet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final ConcurrentHashMap<Long, Pet> pets = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public void createPet(Pet pet) {
        pet.setPetId(idCounter.getAndIncrement());
        pets.put(pet.getPetId(), pet);
    }

    public Pet getPet(long id) {
        return pets.get(id);
    }

    public void updatePet(Pet pet) {
        pets.put(pet.getPetId(), pet);
    }

    public void deletePet(long id) {
        pets.remove(id);
    }

    public List<Pet> getAllPets() {
        return new ArrayList<>(pets.values());
    }

    public List<Pet> getPetsByName(String name) {
        return pets.values().stream()
                .filter(pet -> pet.getName().equals(name))
                .toList();
    }
}
