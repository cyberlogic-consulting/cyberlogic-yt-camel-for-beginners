package ch.cyberlogic.camel.examples.service;

import ch.cyberlogic.camel.examples.model.Pet;
import ch.cyberlogic.camel.examples.model.cxf.GetPetsResponse;
import ch.cyberlogic.camel.examples.model.cxf.NoSuchPetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service("petServiceFromJava")
public class PetServiceImpl implements PetService {
    private final ConcurrentHashMap<Long, Pet> pets = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public void addPet(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(idCounter.getAndIncrement());
        }
        pets.put(pet.getId(), pet);
    }

    @Override
    public Pet getPet(Long id) throws NoSuchPetException {
        if (!pets.containsKey(id)) {
            throw new NoSuchPetException(id);
        }
        return pets.get(id);
    }

    @Override
    public GetPetsResponse getPets() {
        GetPetsResponse result = new GetPetsResponse();
        result.setPets(pets.values());
        return result;
    }

    @Override
    public void updatePet(Long id, Pet Pet) throws NoSuchPetException {
        if (!pets.containsKey(id)) {
            throw new NoSuchPetException(id);
        }
        pets.put(id, Pet);
    }

    @Override
    public void removePet(Long id) throws NoSuchPetException {
        if (!pets.containsKey(id)) {
            throw new NoSuchPetException(id);
        }
        pets.remove(id);
    }
}
