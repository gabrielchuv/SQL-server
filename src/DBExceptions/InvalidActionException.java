package DBExceptions;

public class InvalidActionException extends Exception {
    private String token;

    public InvalidActionException(String token) {
        this.token = token;
    }
    public InvalidActionException() {

    }

    public String getToken() {
        return token;
    }
}
