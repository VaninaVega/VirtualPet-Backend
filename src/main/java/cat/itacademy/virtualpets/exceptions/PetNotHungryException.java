package cat.itacademy.virtualpets.exceptions;

public class PetNotHungryException extends PetStatusException {
    public PetNotHungryException(String message) {
        super(message);
    }
}
