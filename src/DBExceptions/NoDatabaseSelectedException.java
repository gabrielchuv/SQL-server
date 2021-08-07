package DBExceptions;

public class NoDatabaseSelectedException extends InvalidActionException {
    public NoDatabaseSelectedException() {
    }

    public String toString() {
        return "No database was selected!";
    }
}
