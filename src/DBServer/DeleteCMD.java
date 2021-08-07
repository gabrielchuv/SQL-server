package DBServer;

import DBExceptions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteCMD extends DBCmd {
    public DeleteCMD() {

    }

    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException, IOException {

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

        /* Column doesn't exist - condition */
        for(int i = 0; i < getCondition().size(); i++) {
            if(!server.db.getDatabase().get(getTableName()).getHeader().contains(getCondition().get(i).getColName().toUpperCase())) {
                throw new InvalidColumnNameException(getCondition().get(i).getColName());
            }
        }

        server.db.getDatabase().get(getTableName()).setTable(getCondition(), getConditionKeywords());

        /* Update table file in filesystem */
        DBUpdater updater = new DBUpdater(server.db.getDatabase().get(getTableName()).getTable(),
                server.getDatabaseName() + File.separator + getTableName() + ".tab");
        updater.updateTable();

        return null;
    }
}
