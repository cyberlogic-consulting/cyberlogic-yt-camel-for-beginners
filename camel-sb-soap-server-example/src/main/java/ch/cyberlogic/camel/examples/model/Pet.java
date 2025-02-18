package ch.cyberlogic.camel.examples.model;

import ch.cyberlogic.camel.examples.model.cxf.PetType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class Pet {
	private Long id;

	@Size(min = 1, max = 15)
	private String name;

	@NotEmpty
	private String status;

	@Valid
	private PetType type;

	@NotEmpty
	private LocalDate birthDate;


	public Pet() {
	}

	public Pet(Long id, String name, String status, PetType type, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.type = type;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public @Size(min = 1, max = 15) String getName() {
		return name;
	}

	public void setName(@Size(min = 1, max = 15) String name) {
		this.name = name;
	}

	public @NotEmpty String getStatus() {
		return status;
	}

	public void setStatus(@NotEmpty String status) {
		this.status = status;
	}

	public @Valid PetType getType() {
		return type;
	}

	public void setType(@Valid PetType type) {
		this.type = type;
	}

	public @NotEmpty LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(@NotEmpty LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pet pet = (Pet) o;
		return Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(status, pet.status) && type == pet.type && Objects.equals(birthDate, pet.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, status, type, birthDate);
	}

	@Override
	public String toString() {
		return "Pet{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status='" + status + '\'' +
				", type=" + type +
				", birthDate=" + birthDate +
				'}';
	}
}
