package cat.itacademy.virtualpets.exceptions;

public class PetAlreadyRestedException extends PetStatusException {
    public PetAlreadyRestedException(String message) {
        super(message);
    }
}
