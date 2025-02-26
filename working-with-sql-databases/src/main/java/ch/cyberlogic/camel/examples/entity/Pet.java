package ch.cyberlogic.camel.examples.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    public Pet() {
    }

    public Pet(Long id, String name, String status, PetType type, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.type = type;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetType type;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public enum PetType {
        Dog, Cat, Lizard
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

