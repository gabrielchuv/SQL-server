package DBExceptions;

public class InvalidKeywordException extends InvalidTokenException {

    public InvalidKeywordException(String token) {
        super(token);
    }

    public String toString() {
        return "Invalid keyword: " + getToken();
    }
}
