package DBServer;

import java.io.*;
import java.util.ArrayList;

public class DBReader {

    private File directory;
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> names;

    public DBReader(String folderName) {
        directory = new File(folderName);
        data = new ArrayList<ArrayList<String>>();
        names = new ArrayList<String>();
    }

    public void readDatabase() throws IOException {
        File[] contents = directory.listFiles();

        for(int i = 0; i < contents.length; i++) {
            data.add(readTable(contents[i].getPath()));
        }
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public ArrayList<String> getFileNames() {
        File[] contents = directory.listFiles();
        for(File f : contents) {
            names.add(f.getName());
        }
        return names;
    }

    private ArrayList<String> readTable(String filePath) throws IOException {
        File fileToOpen = new File(filePath);
        FileReader reader = new FileReader(fileToOpen);
        BufferedReader buffReader = new BufferedReader(reader);
        String row;
        ArrayList<String> table = new ArrayList<String>();

        while ((row = buffReader.readLine()) != null) {
            table.add(row);

        }
        buffReader.close();

      return table;
    }

}
