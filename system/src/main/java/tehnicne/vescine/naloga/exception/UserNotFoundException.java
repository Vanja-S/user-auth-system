package tehnicne.vescine.naloga.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username) {
        super("User with username " + username + " was not found.");
    }

    public UserNotFoundException(Long userID) {
        super("User with ID " + userID + " was not found.");
    }
}
