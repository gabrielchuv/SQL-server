package DBServer;

import DBExceptions.InvalidActionException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class DBCmd {

    private String tableName;
    private String DBName;
    private ArrayList<String> values = new ArrayList<String>();
    private ArrayList<String> colNames = new ArrayList<String>();
    private ArrayList<Condition> conditions = new ArrayList<Condition>();
    private ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    private String commandType;
    private Join joinValues;
    private ArrayList<Keyword> conditionKeywords = new ArrayList<Keyword>();

    public abstract ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException, IOException;

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public String getDBName() {
        return DBName;
    }

    public void setJoinValues(Join joinValues) {
        this.joinValues = joinValues;
    }

    public Join getJoinValues() {
        return this.joinValues;
    }

    public void setNameValuePairs(NameValuePair nameValuePair) {
        nameValuePairs.add(nameValuePair);
    }

    public ArrayList<NameValuePair> getNameValuePairs() {
        return nameValuePairs;
    }

    public void setConditions(Condition condition) {
        conditions.add(condition);
    }

    public ArrayList<Condition> getCondition() {
        return conditions;
    }

    public void setColNames(String colName) {
        colNames.add(colName);
    }

    public void setIDCol(String colName) {
        colNames.add(0, colName);
    }

    public ArrayList<String> getColNames() {
        return colNames;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return this.commandType;
    }

    public void setValues(String value) {
        this.values.add(value);
    }
    public ArrayList<String> getValues() {
        return this.values;
    }

    public void setIDValue(String id) {
        values.add(0,id);
    }

    public void setConditionKeywords(Keyword keyword) {
        conditionKeywords.add(keyword);
    }

    public ArrayList<Keyword> getConditionKeywords() {
        return conditionKeywords;
    }
}


