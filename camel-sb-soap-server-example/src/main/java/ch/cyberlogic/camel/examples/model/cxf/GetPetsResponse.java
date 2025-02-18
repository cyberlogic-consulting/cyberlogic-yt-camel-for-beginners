package ch.cyberlogic.camel.examples.model.cxf;

import ch.cyberlogic.camel.examples.model.Pet;
import java.util.Collection;

public class GetPetsResponse {
	private Collection<Pet> Pets;

	public Collection<Pet> getPets() {
		return Pets;
	}

	public void setPets(Collection<Pet> Pets) {
		this.Pets = Pets;
	}
}
