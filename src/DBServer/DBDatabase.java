package DBServer;

import java.util.ArrayList;
import java.util.HashMap;

public class DBDatabase {

    private HashMap<String, DBTable> database;

    public DBDatabase() {
        database = new HashMap<String, DBTable>();
    }

    public void setDatabase(ArrayList<ArrayList<String>> data, ArrayList<String> fileNames) {
        String[] fileName;
        for(int i = 0; i < data.size(); i++) {
            DBTable table = new DBTable(data.get(i));
            table.setTable();
            fileName = fileNames.get(i).split("\\.tab");
            database.put(fileName[0], table);
        }
    }

    public void setTableName(String tableName) {
        DBTable table = new DBTable();
        database.put(tableName, table);
    }

    public HashMap<String, DBTable> getDatabase() {
        return database;
    }

    public ArrayList<ArrayList<String>> getJoinTable(Join joinValues) {
        ArrayList<ArrayList<String>> joinTable = new ArrayList<ArrayList<String>>();

       /* Adding table header */
        ArrayList<String> header = new ArrayList<String>();
        addValuesToRow(header, database.get(joinValues.getTableOne()).getJoinHeader(joinValues.getTableOne()));
        addValuesToRow(header, database.get(joinValues.getTableTwo()).getJoinHeader(joinValues.getTableTwo()));
        header.add(0,"ID");
        joinTable.add(header);

        for(int i = 0; i < database.get(joinValues.getTableOne()).getTable().size() - 1; i++) {
            for(int j = 0; j < database.get(joinValues.getTableTwo()).getTable().size() - 1; j++) {
                if(database.get(joinValues.getTableOne()).getFullTable().get(i).getJoinValue(joinValues.getAttributeOne().toUpperCase()).equals
                        (database.get(joinValues.getTableTwo()).getFullTable().get(j).getJoinValue(joinValues.getAttributeTwo().toUpperCase()))) {
                    /* Adding table row */
                    ArrayList<String> row = new ArrayList<String>();
                    addValuesToRow(row, database.get(joinValues.getTableOne()).getFullTable().get(i).getJoinValues());
                    addValuesToRow(row, database.get(joinValues.getTableTwo()).getFullTable().get(j).getJoinValues());
                    addIDToRow(row, joinTable.size());
                    joinTable.add(row);
                }
            }
        }
        return joinTable;
    }

    private void addValuesToRow(ArrayList<String> row, ArrayList<String> values) {
        for(int i = 0; i < values.size(); i++) {
            row.add(values.get(i));
        }
    }

    private void addIDToRow(ArrayList<String> row, Integer id) {
        row.add(0, id.toString());
    }

}
