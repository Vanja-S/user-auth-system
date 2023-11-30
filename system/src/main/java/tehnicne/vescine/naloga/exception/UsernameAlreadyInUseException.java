package tehnicne.vescine.naloga.exception;

public class UsernameAlreadyInUseException extends Exception {
    public UsernameAlreadyInUseException(String message) {
        super("Username already in use: " + message);
    }
}
