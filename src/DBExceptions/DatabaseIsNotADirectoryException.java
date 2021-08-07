package DBExceptions;

public class DatabaseIsNotADirectoryException extends InvalidActionException {
    public DatabaseIsNotADirectoryException(String token) {
        super(token);
    }

    public String toString() {
        return "File " + getToken() + " is not a folder";
    }
}
