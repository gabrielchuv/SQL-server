package DBServer;

public class Join {

    private String tableOne;
    private String tableTwo;
    private String attributeOne;
    private String attributeTwo;

    public Join() {
    }

    public void setTableOne(String tableName) {
        tableOne = tableName;
    }

    public String getTableOne() {
        return tableOne;
    }

    public void setTableTwo(String tableName) {
        tableTwo = tableName;
    }

    public String getTableTwo() {
        return tableTwo;
    }

    public void setAttributeOne(String attributeName) {
        attributeOne = attributeName;
    }

    public String getAttributeOne() {
        return attributeOne;
    }

    public void setAttributeTwo(String attributeName) {
        attributeTwo = attributeName;
    }

    public String getAttributeTwo() {
        return attributeTwo;
    }
}
