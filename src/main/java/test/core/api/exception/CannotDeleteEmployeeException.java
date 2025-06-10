package test.core.api.exception;
public class CannotDeleteEmployeeException extends Exception {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}
