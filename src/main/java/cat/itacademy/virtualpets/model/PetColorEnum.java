package cat.itacademy.virtualpets.model;


public enum PetColorEnum {

    BROWN("Brown"),
    VIOLET("Violet"),
    STRIPED("Striped");

    private final String color;

    PetColorEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
