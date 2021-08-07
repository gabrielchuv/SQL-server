package DBServer;

import DBExceptions.DatabaseIsNotADirectoryException;
import DBExceptions.InvalidActionException;
import DBExceptions.FileDoesNotExistException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UseCMD extends DBCmd{

    public UseCMD() {
    }

    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException {

        File database = new File(getDBName());
        /* Database doesn't exist*/
        if(!database.exists()) {
            throw new FileDoesNotExistException(getDBName());
        }
        /* Database is not a folder */
        if(!database.isDirectory()) {
            throw new DatabaseIsNotADirectoryException(getDBName());
        }

        DBReader reader = new DBReader(getDBName());
        try {
            reader.readDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.db = new DBDatabase();
        server.db.setDatabase(reader.getData(), reader.getFileNames());
        server.setDatabaseName(getDBName());

        return null;
    }



}
