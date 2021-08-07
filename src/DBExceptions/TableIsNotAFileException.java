package DBExceptions;

public class TableIsNotAFileException extends InvalidActionException {
    public TableIsNotAFileException(String token) {
        super(token);
    }

    public String toString() {
        return "Table " + getToken() + " is not a file.";
    }
}
