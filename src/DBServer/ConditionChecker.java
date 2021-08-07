package DBServer;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionChecker {

    private ArrayList<Condition> conditions;
    private HashMap<String, String> row;
    private ArrayList<Keyword> conditionKeywords;
    private Integer counter;
    private ArrayList<Boolean> conditionsOutput;

    public ConditionChecker(ArrayList<Condition> conditions, HashMap<String, String> row, ArrayList<Keyword> conditionKeywords) {
        this.conditions = conditions;
        this.row = row;
        this.conditionKeywords = conditionKeywords;
        this.counter = 0;
    }

    private boolean evaluateCondition(Condition condition) {

        switch (condition.getOperator()) {
            case "==":
                return row.get(condition.getColName().toUpperCase()).equals(condition.getValue());
            case "!=":
                return !row.get(condition.getColName().toUpperCase()).equals(condition.getValue());
            case ">":
                return Float.parseFloat(row.get(condition.getColName().toUpperCase())) > Float.parseFloat(condition.getValue());
            case "<":
                return Float.parseFloat(row.get(condition.getColName().toUpperCase())) < Float.parseFloat(condition.getValue());
            case ">=":
                return Float.parseFloat(row.get(condition.getColName().toUpperCase())) >= Float.parseFloat(condition.getValue());
            case "<=":
                return Float.parseFloat(row.get(condition.getColName().toUpperCase())) <= Float.parseFloat(condition.getValue());
            case "LIKE":
                return row.get(condition.getColName().toUpperCase()).contains(condition.getValue());
        }
        return false;
    }

    public void sort() {
        sortConditionKeywords(conditionKeywords);
        sortConditions(this.conditions);
        this.conditionsOutput = evaluateConditions(conditions);
    }

    public boolean checkConditions() {
        if(check(this.conditionsOutput.get(0))) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean check(Boolean condition) {
        if(counter == conditionsOutput.size() - 1) {
            return condition;
        }
        if(counter == conditionKeywords.size()) {
            return condition;
        }

        if(conditionKeywords.get(counter).getKeyword().equals("AND")) {
            counter++;

            if(check(andCondition(condition, conditionsOutput.get(counter)))) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            counter++;
            if(check(orCondition(condition, conditionsOutput.get(counter)))) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean andCondition(Boolean conditionOne, Boolean conditionTwo) {
        if((conditionOne) && (conditionTwo)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean orCondition(Boolean conditionOne, Boolean conditionTwo) {
        if((!conditionOne) && (!conditionTwo)) {
            return false;
        }
        else {
            return true;
        }
    }

    private void sortConditionKeywords(ArrayList<Keyword> conditionKeywords) {
        int length = conditionKeywords.size();
        Keyword temporaryKeyword = new Keyword();
        for(int i = 0; i < length - 1; i++) {
            for(int j = 0; j < length - i - 1; j++) {
                if(conditionKeywords.get(j + 1).getLevel() > conditionKeywords.get(j).getLevel()) {
                    temporaryKeyword = conditionKeywords.get(j);
                    conditionKeywords.set(j, conditionKeywords.get(j + 1));
                    conditionKeywords.set(j + 1, temporaryKeyword);
                }
            }
        }
    }


    private void sortConditions(ArrayList<Condition> conditions) {
        int length = conditions.size();
        Condition temporaryCondition = new Condition();
        for(int i = 0; i < length - 1; i++) {
            for(int j = 0; j < length - i - 1; j++) {
                if(conditions.get(j + 1).getLevel() > conditions.get(j).getLevel()) {
                    temporaryCondition = conditions.get(j);
                    conditions.set(j, conditions.get(j + 1));
                    conditions.set(j + 1, temporaryCondition);
                }
            }
        }
    }


    private ArrayList<Boolean> evaluateConditions(ArrayList<Condition> conditions) {
        ArrayList<Boolean> conditionsOutput = new ArrayList<Boolean>();
        Boolean temporary;

        for(int i = 0; i < conditions.size(); i++) {
            temporary = evaluateCondition(conditions.get(i));
            conditionsOutput.add(temporary);
        }
        return conditionsOutput;
    }
}