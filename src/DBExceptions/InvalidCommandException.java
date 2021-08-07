package DBExceptions;

public class InvalidCommandException extends InvalidTokenException {
    public InvalidCommandException(String token) {
        super(token);
    }

    public String toString() {
        return "Invalid command: " + getToken();
    }
}
