package cat.itacademy.virtualpets.exceptions;

public class PetHasNotEnergyException extends PetStatusException {
    public PetHasNotEnergyException(String message) {
        super(message);
    }
}
