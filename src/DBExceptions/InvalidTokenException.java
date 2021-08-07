package DBExceptions;

public class InvalidTokenException extends Exception {

    private String token;

    public InvalidTokenException(String token) {
        this.token = token;
    }

    public InvalidTokenException() {

    }

    public String getToken() {
        return token;
    }
}
