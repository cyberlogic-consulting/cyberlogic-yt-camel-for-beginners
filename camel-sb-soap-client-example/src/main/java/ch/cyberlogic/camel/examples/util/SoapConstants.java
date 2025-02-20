package ch.cyberlogic.camel.examples.util;

import ch.cyberlogic.camel.examples.petservice.PetService;
import ch.cyberlogic.camel.examples.petservice.PetType;

public class SoapConstants {
    public static final String NAMESPACE = "http://petservice.examples.camel.cyberlogic.ch/";
    public static final String GET_PETS_BY_NAME_OPERATION = "getPetsByName";
    public static final String UPDATE_PET_OPERATION = "updatePet";
    public static final String SERVICE_CLASS = PetService.class.getName();
    public static final String EXAMPLE_NAME = "doggie";
    public static final String[] PET_STATUSES = {"Available", "Taken"};
    public static final PetType[] PET_TYPES = {PetType.DOG, PetType.CAT, PetType.LIZARD};
}
