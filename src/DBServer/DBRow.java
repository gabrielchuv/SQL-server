package DBServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBRow {
    private HashMap<String,String> row;
    private ArrayList<String> header;
    private String rowLine;

    public DBRow(String rowLine, ArrayList<String> header) {
        row = new HashMap<String, String>();
        this.rowLine = rowLine;
        this.header = new ArrayList<String>();
        this.header = header;
    }

    public void setRow() {
        String[] elements;
        elements = extractElements(rowLine);
        for(int i = 0; i < elements.length; i++) {
            row.put(header.get(i),elements[i]);
        }
    }

    /* ALTER command */
    public void addEmptyCell(String header) {
        row.put(header, " ");
    }

    /* ALTER command */
    public void deleteCell(String header) {
        row.remove(header);
    }

    /* DELETE command */
    public boolean rowMeetsConditions(ArrayList<Condition> conditions, ArrayList<Keyword> conditionKeywords) {
        for (int i = 0; i < row.size(); i++) {
            ConditionChecker checker = new ConditionChecker(conditions,row,conditionKeywords);
            checker.sort();
            if(checker.checkConditions()) {
                return true;
            }
        }
        return false;
    }

    /* UPDATE command */
    public void setRow(ArrayList<NameValuePair> nameValuePairs, ArrayList<Condition> conditions, ArrayList<Keyword> conditionKeywords) {
        for(int i = 0; i < row.size(); i++) {
            ConditionChecker checker = new ConditionChecker(conditions,row,conditionKeywords);
            checker.sort();
            if(checker.checkConditions()) {
                updateRow(nameValuePairs);
            }
        }
    }

    /* JOIN command */
    public ArrayList<String> getJoinValues() {
        ArrayList<String> joinValues = new ArrayList<>();
        for(int i = 0; i < this.row.size(); i++) {
            if(!header.get(i).equals("ID")) {
                joinValues.add(this.row.get(header.get(i)));
            }
        }
        return joinValues;
    }

    public ArrayList<String> getRow(List<String> colNames) {
        ArrayList<String> row = new ArrayList<String>();

        if(colNames.get(0).equals("*")) {
            for(int i = 0; i < this.row.size(); i++) {
                row.add(this.row.get(header.get(i)));
            }
        }
        else {

            for(int i = 0; i < colNames.size(); i++) {
                row.add(this.row.get(colNames.get(i)));
            }
        }
        return row;
    }

    /* SELECT command - conditions */
    public ArrayList<String> getRow(List<String> colNames, ArrayList<Condition> conditions, ArrayList<Keyword> conditionKeywords) throws RuntimeException {
        ArrayList<String> queryRow = new ArrayList<String>();

        if (colNames.get(0).equals("*")) {
            for (int i = 0; i < row.size(); i++) {
                ConditionChecker checker = new ConditionChecker(conditions,row,conditionKeywords);

                checker.sort();

                if(checker.checkConditions()) {
                    queryRow.add(row.get((header.get(i))));
                }
            }
        }
        else {
            for (int i = 0; i < colNames.size(); i++) {
                ConditionChecker checker = new ConditionChecker(conditions,row,conditionKeywords);

                checker.sort();

                if(checker.checkConditions()) {
                    queryRow.add(row.get((colNames.get(i))));
                }
            }
        }
        return queryRow;
    }

    public String getJoinValue(String attributeName) {
        return row.get(attributeName);
    }

    public Integer getRowID() {
        return Integer.parseInt(row.get("ID"));
    }

    /* UPDATE command */
    private void updateRow(ArrayList<NameValuePair> nameValuePairs) {
        for(int i = 0; i < row.size(); i++) {
            for(int j = 0; j < nameValuePairs.size(); j++) {
                if(header.get(i).equals(nameValuePairs.get(j).getName().toUpperCase())) {
                    row.put(header.get(i),nameValuePairs.get(j).getValue());
                }
            }
        }
    }

    private String[] extractElements(String rowLine) {
        String[] elements;
        elements = rowLine.split("\t");
        return elements;
    }

}
