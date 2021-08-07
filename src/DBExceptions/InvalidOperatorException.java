package DBExceptions;

public class InvalidOperatorException extends InvalidTokenException {

    public InvalidOperatorException(String token) {
        super(token);
    }

    public String toString() {
        return "Invalid operator: " + getToken();
    }
}
