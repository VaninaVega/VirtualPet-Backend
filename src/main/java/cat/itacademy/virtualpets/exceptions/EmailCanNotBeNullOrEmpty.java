package cat.itacademy.virtualpets.exceptions;

public class EmailCanNotBeNullOrEmpty extends RuntimeException {
    public EmailCanNotBeNullOrEmpty(String message) {
        super(message);
    }
}
