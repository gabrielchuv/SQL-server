package DBExceptions;

public class InvalidColumnNameException extends InvalidActionException {
    public InvalidColumnNameException(String token) {
        super(token);
    }

    public String toString() {
        return "Column " + getToken() + " does not exist in this table";
    }
}
