package cat.itacademy.virtualpets.exceptions;

public class UsernameCanNotBeNullOrEmpty extends RuntimeException {
    public UsernameCanNotBeNullOrEmpty(String message) {
        super(message);
    }
}
