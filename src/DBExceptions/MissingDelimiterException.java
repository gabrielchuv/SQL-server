package DBExceptions;

public class MissingDelimiterException extends InvalidTokenException{

    public MissingDelimiterException() {
        super();
    }


    public String toString() {
        return "Are you missing an apostrophe, parenthesis or semicolon?";
    }
}
