package cr.ac.database.managers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DBManager implements IDBManager {

    // <editor-fold defaultstate="collapsed" desc="constructors">
    protected DBManager(String description, String serverURL)
            throws IOException {
        this.configuration = new Properties();
        this.description = description;
        this.serverURL = (serverURL != null) ? serverURL : SERVER_DEFAULT;

        String path = getConfigurationPath();
        System.out.printf("Database configuration file: '%s'%n", path);
        configuration.load(getClass().getResourceAsStream(path));
    }

    protected DBManager(String description)
            throws IOException {
        this(description, null);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="methods">
    @Override
    public Connection getConnection(String connectionString)
            throws SQLException {
        return cnx = DriverManager.getConnection(connectionString);
    }

    @Override
    public void closeConnection() {
        if (cnx != null) {
            try {
                cnx.close();
            } catch (SQLException ex) {
                System.err.printf("Exception: '%s'%n", ex.getMessage());
            }
        }
    }

    public static DBManager getDBManager(DB_MGR serverType, String serverURL)
            throws InstantiationException,
            ClassNotFoundException,
            IllegalAccessException,
            IOException {
        if (instance == null) {
            switch (serverType) {

                case MYSQL_SERVER:
                    instance = new MySQLDBManager(serverURL);
                    break;

                default:
                    throw new InstantiationException("Server type is not implemented.");
            }
        }
        return instance;
    }

    public static DBManager getDBManager(DB_MGR serverType)
            throws InstantiationException,
            ClassNotFoundException,
            IllegalAccessException,
            IOException {
        return DBManager.getDBManager(serverType, SERVER_DEFAULT);
    }

    public static DBManager getDBManager()
            throws InstantiationException {
        if (instance == null) {
            throw new InstantiationException("Invalid instance.");
        }
        return instance;
    }

    protected String getConfigurationPath() {
        return CONFIGURATION_BASE_PATH;
    }

    @Override
    public Properties getConfiguration() {
        return configuration;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getServerURL() {
        return serverURL;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append(String.format("%s server: @%s",
                getDescription(), getServerURL()));
        return r.toString();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="attributes">
    public static enum DB_MGR {
        MSSQL_SERVER,
        MYSQL_SERVER,
        ODBC_SERVER,
        ORACLE_SERVER,
        POSTGRESQL_SERVER,
    };

    protected static final String CONFIGURATION_BASE_PATH = "./configuration";
    protected static final String SERVER_DEFAULT = "localhost";
    protected static DBManager instance = null;

    protected Properties configuration = null;
    private String description = null;
    protected String serverURL = null;
    protected Connection cnx = null;
    //</editor-fold>
}
