package DBExceptions;

public class NonAlphaNumericException extends InvalidTokenException{

    public NonAlphaNumericException(String token) {
        super(token);

    }

    public String toString() {
        return "Non alpha-numeric token: " + getToken();
    }
}
