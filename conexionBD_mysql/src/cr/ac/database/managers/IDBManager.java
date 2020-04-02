package cr.ac.database.managers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface IDBManager {

    Connection getConnection(String database, String user, String password)
            throws SQLException;

    Connection getConnection(String connectionString)
            throws SQLException;

    void closeConnection();

    Properties getConfiguration();

    String getDescription();

    String getServerURL();

}
