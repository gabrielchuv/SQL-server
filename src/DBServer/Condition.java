package DBServer;

public class Condition {
    private String colName;
    private String operator;
    private String value;
    private String keyword;
    private Integer level;

    public Condition() {

    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getColName() {
        return this.colName;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getValue() {
        return this.value;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }


}
