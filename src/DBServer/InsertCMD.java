package DBServer;

import DBExceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InsertCMD extends DBCmd {
    public InsertCMD() {
    }

    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException, IOException {

        /* No DB selected */
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

        setIDValue(server.db.getDatabase().get(getTableName()).getNewID().toString());
        server.db.getDatabase().get(getTableName()).addRow(getValues());

        /* Update table file in filesystem */
        DBUpdater updater = new DBUpdater(server.db.getDatabase().get(getTableName()).getTable(),
                server.getDatabaseName() + File.separator + getTableName() + ".tab");
        updater.updateTable();

        return null;
    }

}
