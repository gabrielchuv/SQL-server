package DBServer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DBUpdater {

    private ArrayList<ArrayList<String>> table;
    private String path;

    public DBUpdater(ArrayList<ArrayList<String>> table, String path) {
        this.table = new ArrayList<ArrayList<String>>(table);
        this.path = path;
    }

    public DBUpdater(String path) {
        this.path = path;
    }

    public void updateTable() throws IOException {
        File fileToWrite = new File(path);
        FileWriter writer = new FileWriter(fileToWrite);
        BufferedWriter buffWriter = new BufferedWriter(writer);

        StringBuilder tableString = new StringBuilder();

        for(int i = 0; i < table.size(); i++) {
            for(int j = 0; j < table.get(i).size(); j++) {
                tableString.append(table.get(i).get(j));
                if(j != table.get(i).size() - 1) {
                    tableString.append("\t");
                }
            }
                tableString.append("\n");
        }
        buffWriter.write(String.valueOf(tableString));
        buffWriter.flush();
        buffWriter.close();
    }

    /* DROP command */
    public void deleteTable() throws IOException {
        File fileToDelete = new File(path);

        if(!fileToDelete.delete()) {
            throw new IOException("Couldn't delete table!");
        }
    }

    public void deleteDatabase() throws IOException {
        File databaseToDelete = new File(path);
        File[] contents = databaseToDelete.listFiles();

        if(contents.length > 0) {
            for(int i = 0; i < contents.length; i++) {
                File tableToDelete = new File(contents[i].getPath());
                if(!tableToDelete.delete()) {
                    throw new IOException("Couldn't delete table!");
                }
            }
        }

        if(!databaseToDelete.delete()) {
            throw new IOException("Couldn't delete table!");
        }
    }

}
