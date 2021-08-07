package DBServer;

import DBExceptions.InvalidActionException;
import DBExceptions.InvalidInsertException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBTable {

    private ArrayList<DBRow> table;
    private ArrayList<String> tableHeader;
    private ArrayList<String> data;

    public DBTable(ArrayList<String> data) {
        tableHeader = new ArrayList<String>();
        this.data = new ArrayList<String>(data);
        table = new ArrayList<DBRow>();
    }

    public DBTable() {
        tableHeader = new ArrayList<String>();
        table = new ArrayList<DBRow>();
    }

    public void setTable() {
        for(int i = 0; i < data.size(); i++) {
            if(i == 0) {
                setHeader(data.get(i));
            }
            else {
                setBody(data.get(i));
            }
        }
    }

    public void setHeader(String header) {
        String[] elements;
        elements = extractElements(header);
        tableHeader.addAll(Arrays.asList(elements));
    }

    public void setHeader(ArrayList<String> header) {
        tableHeader.addAll(header);
    }

    public ArrayList<String> getHeader() {
        return tableHeader;
    }

    public void setBody(String rowLine) {
            DBRow row = new DBRow(rowLine, tableHeader);
            row.setRow();
            table.add(row);
    }

    /* SELECT command */
    public ArrayList<ArrayList<String>> getTable(List<String> colNames) {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

        if(colNames.get(0).equals("*")) {
            table.add(tableHeader);
            for(int i = 0; i < this.table.size(); i++) {
                table.add(this.table.get(i).getRow(colNames));
            }
        }
        else {
            table.add((ArrayList<String>) colNames);
            for(int i = 0; i < this.table.size(); i++) {
            table.add(this.table.get(i).getRow(colNames));
            }
        }
        return table;
    }

    public ArrayList<DBRow> getFullTable() {
        return table;
    }

    /* SELECT command - conditions */
    public ArrayList<ArrayList<String>> getTable(List<String> colNames, ArrayList<Condition> conditions, ArrayList<Keyword> conditionKeywords) throws RuntimeException {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        ArrayList<String> row;

        if(colNames.get(0).equals("*")) {
            table.add(tableHeader);
            for(int i = 0; i < this.table.size(); i++) {
                if((row = this.table.get(i).getRow(colNames,conditions, conditionKeywords)).size() > 0) {
                    table.add(row);
                }
            }
        }
        else {
            table.add((ArrayList<String>) colNames);
            for(int i = 0; i < this.table.size(); i++) {
                if((row = this.table.get(i).getRow(colNames,conditions, conditionKeywords)).size() > 0) {
                    table.add(row);
                }
            }
        }
        if(table.size() == 1) {
            return null;
        }
        return table;
    }

    /* UPDATE command */
    public void setTable(ArrayList<NameValuePair> nameValuePairs, ArrayList<Condition> conditions, ArrayList<Keyword> conditionKeywords) {
        for(int i = 0; i < table.size(); i++) {
            table.get(i).setRow(nameValuePairs, conditions, conditionKeywords);
        }
    }

    /* ALTER command */
    public void addColumn(String colName) {
        tableHeader.add(colName.toUpperCase());
        for(int i = 0; i < table.size(); i++) {
            table.get(i).addEmptyCell(colName.toUpperCase());
        }
    }

    /* ALTER command */
    public void deleteColumn(String colName) {
        tableHeader.remove(tableHeader.size() - 1);
        for(int i = 0; i < table.size(); i++) {
            table.get(i).deleteCell(colName.toUpperCase());
        }
    }

    /* DELETE command */
    public void setTable(ArrayList<Condition> conditions, ArrayList<Keyword> conditionKeywords) {
        for(int i = table.size() - 1; i >= 0; i--) {
            if(table.get(i).rowMeetsConditions(conditions, conditionKeywords)) {
                table.remove(i);
            }
        }
    }

    /* INSERT command */
    public void addRow(ArrayList<String> values) throws InvalidActionException {
        if(values.size() != tableHeader.size()) {
            throw new InvalidInsertException();
        }
        DBRow row = new DBRow(fromArrayToString(values), tableHeader);
        row.setRow();
        table.add(row);
    }

    public ArrayList<ArrayList<String>> getTable() {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

        table.add(tableHeader);
        for(int i = 0; i < this.table.size(); i++) {
            table.add(this.table.get(i).getRow(tableHeader));
        }
        return table;
    }

    /* JOIN command */
    public ArrayList<String> getJoinHeader(String tableName) {
        ArrayList<String> header = new ArrayList<>();

        for(int i = 0; i < this.tableHeader.size(); i++) {
            if(!this.tableHeader.get(i).equals("ID")) {
                header.add(tableName.toUpperCase() + "." + this.tableHeader.get(i));
            }
        }
        return header;
    }

    public Integer getNewID() {
        if(table.size() == 0) {
            return 1;
        }
        else {
            return table.get(table.size() - 1).getRowID() + 1;
        }
    }

    private String fromArrayToString(ArrayList<String> array) {
        StringBuilder string = new StringBuilder();

        for(int i = 0; i < array.size(); i++) {
            string.append(array.get(i));
            string.append("\t");
        }
        return string.toString();
    }


    private String[] extractElements(String rowLine) {
        String[] elements;
        elements = rowLine.split("\t");
        return elements;
    }
}
