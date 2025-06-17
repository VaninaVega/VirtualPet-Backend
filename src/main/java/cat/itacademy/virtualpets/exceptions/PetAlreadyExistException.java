package cat.itacademy.virtualpets.exceptions;

public class PetAlreadyExistException extends RuntimeException {
    public PetAlreadyExistException(String message) {
        super(message);
    }
}
