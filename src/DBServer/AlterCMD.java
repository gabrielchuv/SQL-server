package DBServer;

import DBExceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AlterCMD extends DBCmd {
    public AlterCMD() {

    }
    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException, IOException {

        /* DB not selected */
        if (server.getDatabaseName() == null) {
            throw new NoDatabaseSelectedException();
        }
        File table = new File(server.getDatabaseName() + File.separator + getTableName() + ".tab");

        /* Table doesn't exist */
        if (!table.exists()) {
            throw new FileDoesNotExistException(getTableName());
        }
        /* Table is not a file */
        if (!table.isFile()) {
            throw new TableIsNotAFileException(getTableName());
        }

        /* Add column */
        if(getCommandType().equals("ADD")) {
            server.db.getDatabase().get(getTableName()).addColumn(getColNames().get(0));
        }

        /* Delete column */
        else {
            /* Column doesn't exist */
            if(!getColNames().get(0).equals("*") && !server.db.getDatabase().get(getTableName()).getHeader().contains(getColNames().get(0).toUpperCase())) {
                throw new InvalidColumnNameException(getColNames().get(0));
            }
            server.db.getDatabase().get(getTableName()).deleteColumn(getColNames().get(0));
        }

        /* Update table file in filesystem */
        DBUpdater updater = new DBUpdater(server.db.getDatabase().get(getTableName()).getTable(),
                server.getDatabaseName() + File.separator + getTableName() + ".tab");
        updater.updateTable();

        return null;
    }
}
