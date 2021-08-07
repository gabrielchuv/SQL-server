package DBExceptions;

public class DatabaseAlreadyExistsException extends InvalidActionException {
    public DatabaseAlreadyExistsException(String token) {
        super(token);
    }

    public String toString() {
        return "Database " + getToken() + " already exists";
    }
}
