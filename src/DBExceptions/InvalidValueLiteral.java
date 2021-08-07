package DBExceptions;

public class InvalidValueLiteral extends InvalidTokenException {
    public InvalidValueLiteral(String token) {
        super(token);
    }

    public String toString() {
        return "Invalid value literal: " + getToken();
    }
}
