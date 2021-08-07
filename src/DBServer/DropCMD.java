package DBServer;

import DBExceptions.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DropCMD extends DBCmd {
    public DropCMD() {

    }
    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException, IOException {

        if(getCommandType().equals("DATABASE")) {

            File database = new File(getDBName());

            /* Database doesn't exist */
            if(!database.exists()) {
                throw new FileDoesNotExistException(getDBName());
            }
            /* Database is not a folder */
            if(!database.isDirectory()) {
                throw new DatabaseIsNotADirectoryException(getDBName());
            }

            try {
                DBUpdater updater = new DBUpdater(getDBName());
                updater.deleteDatabase();
            }
            catch(IOException exception) {
                exception.printStackTrace();
            }

        }
        else {

            /* NO DB selected */
            if(server.getDatabaseName() == null) {
                throw new NoDatabaseSelectedException();
            }

            File table = new File(server.getDatabaseName() + File.separator + getTableName() + ".tab");

            /* Table doesn't exist */
            if(!table.exists()) {
                throw new FileDoesNotExistException(getTableName());
            }

            /* Table is not a file */
            if(!table.isFile()) {
                throw new TableIsNotAFileException(getTableName());
            }

            /* Update table file in filesystem */
            DBUpdater updater = new DBUpdater(server.getDatabaseName() + File.separator + getTableName() + ".tab");
            updater.deleteTable();
        }
        return null;
    }



}
