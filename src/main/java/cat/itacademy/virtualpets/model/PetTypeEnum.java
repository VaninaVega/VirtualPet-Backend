package cat.itacademy.virtualpets.model;

public enum PetTypeEnum {

    DOG("Dog"),
    CAT("Cat"),
    FISH("Fish");

    private final String type;

    PetTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
