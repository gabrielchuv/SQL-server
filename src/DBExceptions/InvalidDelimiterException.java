package DBExceptions;

public class InvalidDelimiterException extends InvalidTokenException {
    public InvalidDelimiterException(String token) {
        super(token);
    }

    public String toString() {
        return "Invalid delimiter: " + getToken();
    }
}
