package DBServer;

import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {

    private String[] tokens;
    private ArrayList<String> finalTokens;
    private String command;

    public Tokenizer(String command) {
        finalTokens = new ArrayList<String>();
        this.command = command;
    }

    public void tokenize() {
        splitWithDelimiter(removeBlankSpaces(command));
    }

    private String[] removeBlankSpaces(String command) {
        tokens = command.trim().split("\\s+(?=(?:'[^']*'|[^'])*$)");
        return tokens;
    }

    private void splitWithDelimiter(String[] tokens) {
        for(int i = 0; i < tokens.length; i++) {
            String[] token = tokens[i].split("((?<=;)|(?=;))|(?<===)|(?===)|(?<=>(?!=))|(?=>(?!=))|((?<=>=)|" +
                    "(?=>=))|(?<=<(?!=))|(?=<(?!=))|((?<=<=)|(?=<=))|((?<=!=)|(?=!=))|((?<=,)|(?=,))|((?<=\\*)|(?=\\*))" +
                    "|((?<=')|(?='))|((?<=[(])|(?=[(]))|((?<=[)])|(?=[)]))|((?<=(?<![=!><])=(?!=))|(?=(?<![=!><])=(?!=)))");
            addToTokenArrayList(token);
        }
    }

    private void addToTokenArrayList(String[] token) {
        finalTokens.addAll(Arrays.asList(token));
    }

    public ArrayList<String> getTokens() {
        return finalTokens;
    }
}
