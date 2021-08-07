package DBServer;

import DBExceptions.*;

import java.io.File;
import java.util.ArrayList;

public class SelectCMD extends DBCmd {

    public SelectCMD() {
    }

    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException {

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
        /* Column doesn't exist  */
        for(int i = 0; i < getColNames().size(); i++) {
            if(!getColNames().get(i).equals("*") && !server.db.getDatabase().get(getTableName()).getHeader().contains(getColNames().get(i))) {
                throw new InvalidColumnNameException(getColNames().get(i));
            }
        }
        /* Column doesn't exist - condition */
        for(int i = 0; i < getCondition().size(); i++) {
            if(!server.db.getDatabase().get(getTableName()).getHeader().contains(getCondition().get(i).getColName().toUpperCase())) {
                throw new InvalidColumnNameException(getCondition().get(i).getColName());
            }
        }

        if(getCondition().size() == 0) {
            return server.db.getDatabase().get(getTableName()).getTable(getColNames());
        }
        else {
            return server.db.getDatabase().get(getTableName()).getTable(getColNames(),getCondition(),getConditionKeywords());
        }
    }

}
