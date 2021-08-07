package DBExceptions;

public class InvalidInsertException extends InvalidActionException{
    public InvalidInsertException() {
        super();
    }

    public String toString() {
        return "Number of values doesn't match number of existing columns";
    }
}
