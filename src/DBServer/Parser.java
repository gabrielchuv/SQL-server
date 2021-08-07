package DBServer;

import DBExceptions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private Tokenizer tokenizer;
    private ArrayList<String> tokens;
    private int i = 0;
    private DBCmd cmd;
    private String ALPHANUMERIC = "[A-Za-z0-9]+";
    private List<String> operators = Arrays.asList("==",">","<",">=","<=","!=","LIKE");
    private Integer bracketCounter = 0;

    public Parser(String command) {
        tokenizer = new Tokenizer(command);
        tokenizer.tokenize();
        tokens = tokenizer.getTokens();
    }

    public DBCmd command() throws InvalidTokenException {
        commandType();
        if(tokens.size() <= i) {
            throw new MissingDelimiterException();
        }
        if(!tokens.get(i).equals(";")) {
            throw new InvalidDelimiterException(tokens.get(i));
        }
        return cmd;
    }

    private void commandType() throws InvalidTokenException {
        switch (tokens.get(i).toUpperCase()) {
            case "SELECT":
                cmd = new SelectCMD();
                Select();
                break;
            case "UPDATE":
                cmd = new UpdateCMD();
                Update();
                break;
            case "ALTER":
                cmd = new AlterCMD();
                Alter();
                break;
            case "INSERT":
                cmd = new InsertCMD();
                Insert();
                break;
            case "USE":
                cmd = new UseCMD();
                Use();
                break;
            case "CREATE":
                cmd = new CreateCMD();
                Create();
                break;
            case "DROP":
                cmd = new DropCMD();
                Drop();
                break;
            case "JOIN":
                cmd = new JoinCMD();
                Join();
                break;
            case "DELETE":
                cmd = new DeleteCMD();
                Delete();
                break;
            default:
                throw new InvalidCommandException(tokens.get(i));
            }
        }


    private void Select() throws InvalidTokenException {
        i++;
        WildAttribList();
        if(!tokens.get(i).toUpperCase().equals("FROM")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        else {
            cmd.setTableName(tokens.get(i));
        }
        if(tokens.size() - 1 <= i) {
            throw new MissingDelimiterException();
        }
        i++;
        if(tokens.size() - 1 > i){
            if(!tokens.get(i).toUpperCase().equals("WHERE")) {
                throw new InvalidKeywordException(tokens.get(i));
            }
            i++;
            Condition();
        }
    }

    private void Use() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        else {
            cmd.setDBName(tokens.get(i));
        }
        i++;
    }

    private void Create() throws InvalidTokenException {
        i++;
        if(tokens.get(i).toUpperCase().equals("DATABASE")) {
            cmd.setCommandType("DATABASE");
            CreateDatabase();
        }
        else if(tokens.get(i).toUpperCase().equals("TABLE")) {
            cmd.setCommandType("TABLE");
            CreateTable();
        }
        else {
            throw new InvalidKeywordException(tokens.get(i));
        }
    }

    private void Drop() throws InvalidTokenException {
        i++;
        if(!(tokens.get(i).toUpperCase().equals("DATABASE") || tokens.get(i).toUpperCase().equals("TABLE"))) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        else {
            cmd.setCommandType(tokens.get(i));
            i++;
            if(!tokens.get(i).matches(ALPHANUMERIC)) {
                throw new NonAlphaNumericException(tokens.get(i));
            }
            if(tokens.get(i - 1).toUpperCase().equals("DATABASE")) {
                cmd.setDBName(tokens.get(i));
            }
            else {
                cmd.setTableName(tokens.get(i));
            }
            i++;
        }
    }

    private void Alter() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).toUpperCase().equals("TABLE")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setTableName(tokens.get(i));
        i++;
        if(!(tokens.get(i).toUpperCase().equals("DROP") || tokens.get(i).toUpperCase().equals("ADD"))) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        cmd.setCommandType(tokens.get(i));
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setColNames(tokens.get(i));
        i++;
    }


    private void Insert() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).toUpperCase().equals("INTO")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setTableName(tokens.get(i));
        i++;
        if(!tokens.get(i).toUpperCase().equals("VALUES")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).equals("(")) {
            throw new MissingDelimiterException();
        }
        i++;
        ValueList();
        if(!tokens.get(i).equals(")")) {
            throw new MissingDelimiterException();
        }
        i++;
    }

    private void Update() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setTableName(tokens.get(i));
        i++;
        if(!tokens.get(i).toUpperCase().equals("SET")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        NameValueList();
        if(!tokens.get(i).toUpperCase().equals("WHERE")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        Condition();
    }

    private void Delete() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).toUpperCase().equals("FROM")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setTableName(tokens.get(i));
        i++;
        if(!tokens.get(i).toUpperCase().equals("WHERE")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        Condition();

    }

    private void Join() throws InvalidTokenException {
        Join joinValues = new Join();
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        joinValues.setTableOne(tokens.get(i));
        i++;
        if(!tokens.get(i).toUpperCase().equals("AND")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        joinValues.setTableTwo(tokens.get(i));
        i++;
        if(!tokens.get(i).toUpperCase().equals("ON")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        joinValues.setAttributeOne(tokens.get(i));
        i++;
        if(!tokens.get(i).toUpperCase().equals("AND")) {
            throw new InvalidKeywordException(tokens.get(i));
        }
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        joinValues.setAttributeTwo(tokens.get(i));
        i++;
        cmd.setJoinValues(joinValues);
    }

    private void WildAttribList() throws InvalidTokenException {
        if(!tokens.get(i).equals("*")) {
            AttributeList();
            i--;
        }
        else {
            cmd.setColNames("*");
        }
        i++;
    }

    private void AttributeList() throws InvalidTokenException {
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setColNames(tokens.get(i).toUpperCase());
        i++;
        if(tokens.get(i).equals(",")) {
            i++;
            AttributeList();
        }

    }

    private void Condition() throws InvalidTokenException {
        Condition condition = new Condition();

        if(!tokens.get(i).equals("(")) {
            if(!tokens.get(i).matches(ALPHANUMERIC)) {
                throw new NonAlphaNumericException(tokens.get(i));
            }
            condition.setColName(tokens.get(i));
            i++;
            Operator();
            condition.setOperator(tokens.get(i));
            i++;

            Value();

            if(tokens.get(i).equals("'")) {
                condition.setValue(tokens.get(i - 1));
            }
            else {
                condition.setValue(tokens.get(i));
            }
            i++;

            condition.setLevel(bracketCounter);
            cmd.setConditions(condition);
        }
        else {
            bracketCounter++;
            i++;
            Condition();

            if(!tokens.get(i).equals(")")) {
                throw new MissingDelimiterException();
            }
            bracketCounter--;
            i++;
            if(tokens.get(i).toUpperCase().equals("AND") || tokens.get(i).toUpperCase().equals("OR")) {
                Keyword keyword = new Keyword();
                keyword.setKeyword(tokens.get(i));
                keyword.setLevel(bracketCounter);
                cmd.setConditionKeywords(keyword);
                i++;

                Condition();
            }
        }
    }

    private void Value() throws InvalidTokenException {
        if(tokens.get(i).equals("'")) {
            i++;
            if(!tokens.get(i + 1).equals("'")) {
                throw new MissingDelimiterException();
            }
            i++;
        }
        else if(tokens.get(i).matches("^[0-9]+\\.?[0-9]+$|[0-9]")) {

        }
        else {
            BooleanLiteral();
        }
    }
    private void ValueList() throws InvalidTokenException {
        Value();
        if(tokens.get(i).equals("'")) {
            cmd.setValues(tokens.get(i - 1));
        }
        else {
            cmd.setValues(tokens.get(i));
        }
        i++;
        if(tokens.get(i).equals(",")) {
            i++;
            ValueList();
        }
    }

    private void BooleanLiteral() throws InvalidTokenException {
        if(!((tokens.get(i).toUpperCase().equals("TRUE")) || (tokens.get(i).toUpperCase().equals("FALSE")))) {
            throw new InvalidValueLiteral(tokens.get(i));
        }
    }

    private void Operator() throws InvalidTokenException {
        if(!operators.contains(tokens.get(i))) {
            throw new InvalidOperatorException(tokens.get(i));
        }
        if(tokens.get(i).toUpperCase().equals("LIKE")) {
            if(!tokens.get(i + 1).equals("'")) {
                throw new InvalidDelimiterException(tokens.get(i + 1));
            }
            if(tokens.size() > i + 3 && !tokens.get(i + 3).equals("'")) {
                throw new InvalidDelimiterException(tokens.get(i + 3));
            }
        }
    }

    private void CreateDatabase() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setDBName(tokens.get(i));
        i++;
    }

    private void CreateTable() throws InvalidTokenException {
        i++;
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        cmd.setTableName(tokens.get(i));
        i++;

        if(tokens.size() - 1 > i) {
            if(!tokens.get(i).equals("(")) {
                throw new MissingDelimiterException();
            }
            i++;
            AttributeList();
            if(!tokens.get(i).equals(")")) {
                throw new MissingDelimiterException();
            }
            i++;
        }
    }

    private void NameValueList() throws InvalidTokenException {
        NameValuePair();
        i++;
        if(tokens.get(i).equals(",")) {
            i++;
            NameValueList();
        }
    }

    private void NameValuePair() throws InvalidTokenException {
        NameValuePair nameValuePair = new NameValuePair();
        if(!tokens.get(i).matches(ALPHANUMERIC)) {
            throw new NonAlphaNumericException(tokens.get(i));
        }
        nameValuePair.setName(tokens.get(i));
        i++;
        if(!tokens.get(i).equals("=")) {
            throw new InvalidOperatorException(tokens.get(i));
        }
        i++;
        Value();
        nameValuePair.setValue(tokens.get(i));
        cmd.setNameValuePairs(nameValuePair);
    }
}
