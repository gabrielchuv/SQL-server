package DBServer;

import DBExceptions.*;

import java.io.File;
import java.util.ArrayList;

public class JoinCMD extends DBCmd {
    public JoinCMD() {
    }

    public ArrayList<ArrayList<String>> query(DBServer server) throws InvalidActionException {

        /* No DB selected */
        if(server.getDatabaseName() == null) {
            throw new NoDatabaseSelectedException();
        }

        File tableOne = new File(server.getDatabaseName() + File.separator + getJoinValues().getTableOne() + ".tab");

        /* Table 1 doesn't exist */
        if(!tableOne.exists()) {
            throw new FileDoesNotExistException(getJoinValues().getTableOne());
        }
        /* Table 1 is not a file */
        if(!tableOne.isFile()) {
            throw new TableIsNotAFileException(getJoinValues().getAttributeOne());
        }

        File tableTwo = new File(server.getDatabaseName() + File.separator + getJoinValues().getTableTwo() + ".tab");

        /* Table 2 doesn't exist */
        if(!tableTwo.exists()) {
            throw new FileDoesNotExistException(getJoinValues().getTableTwo());
        }
        /* Table 2 is not a file */
        if(!tableTwo.isFile()) {
            throw new TableIsNotAFileException(getJoinValues().getTableTwo());
        }

        /* Attribute 1 doesn't exist  */
        if(!server.db.getDatabase().get(getJoinValues().getTableOne()).getHeader().contains(getJoinValues().getAttributeOne().toUpperCase())) {
            throw new InvalidColumnNameException(getJoinValues().getAttributeOne());
        }

        /* Attribute 2 doesn't exist  */
        if(!server.db.getDatabase().get(getJoinValues().getTableTwo()).getHeader().contains(getJoinValues().getAttributeTwo().toUpperCase())) {
            throw new InvalidColumnNameException(getJoinValues().getAttributeTwo());
        }

        return server.db.getJoinTable(getJoinValues());
    }
}
