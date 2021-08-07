package DBServer;

import DBExceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateCMD extends DBCmd {

    public CreateCMD() {
    }

    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException, IOException {
        /* Create database */
        if(getCommandType().equals("DATABASE")) {
            File database = new File(getDBName());

            /* DB already exists */
            if(database.exists() && database.isDirectory()) {
                throw new DatabaseAlreadyExistsException(getDBName());
            }

            /* IO issue */
            if(!database.mkdir()) {
                throw new IOException("Couldn't create a new database!");
            }
        }

        /* Create table */
        else {
            /* No DB selected */
            if(server.getDatabaseName() == null) {
                throw new NoDatabaseSelectedException();
            }

            File table = new File(server.getDatabaseName() + File.separator + getTableName() + ".tab");

            /* Table already exists */
            if(table.exists() && table.isFile()) {
                throw new TableAlreadyExistsException(getTableName());
            }

            if(!table.createNewFile()) {
                throw new IOException("Couldn't create a new table!");
            }

            server.db.setTableName(getTableName());

            /* Mutate file in memory if there are any input values */
            setIDCol("ID");
            server.db.getDatabase().get(getTableName()).setHeader(getColNames());

            /* Update table header in filesystem */
            DBUpdater updater = new DBUpdater(server.db.getDatabase().get(getTableName()).getTable(getColNames()),
                    server.getDatabaseName() + File.separator + getTableName() + ".tab");
            updater.updateTable();

        }
        return null;
    }
}
