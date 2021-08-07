package DBExceptions;

public class TableAlreadyExistsException extends InvalidActionException{
    public TableAlreadyExistsException(String token) {
        super(token);
    }

    public String toString() {
        return "Table " + getToken() + " already exists";
    }
}
