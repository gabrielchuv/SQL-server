package DBExceptions;

public class FileDoesNotExistException extends InvalidActionException {
    public FileDoesNotExistException(String token) {
        super(token);
    }

    public String toString() {
        return "File " + getToken() + " does not exist";
    }
}
