package cr.ac.database.managers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBManager extends DBManager {

    // <editor-fold defaultstate="collapsed" desc="constructors">
    MySQLDBManager(String serverURL)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException {
        super("MySQL database manager", serverURL);

        try {
            Class.forName(configuration.getProperty("database_driver")).newInstance();
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException ex) {
            System.err.printf("Exception: '%s'%n", ex.getMessage());
        }
    }

    MySQLDBManager()
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            IOException {
        this(SERVER_DEFAULT);
    }

    @Override
    protected String getConfigurationPath() {
        return String.format("%s%s",
                super.getConfigurationPath(),
                MYSQL_CONFIGURATION_PATH);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="methods">
    @Override
    public Connection getConnection(String database, String user, String password)
            throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
        String connectionURL = String.format("%s//%s/%s",
                configuration.getProperty("protocol"),
                configuration.getProperty("server_url"),
                database);
        System.out.printf("Connection: '%s'%n", connectionURL);

        return cnx = DriverManager.getConnection(connectionURL, user, password);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="attributes">
    // Parámetros para la conexión a un servidor de base de datos MySQL.
    protected static final String MYSQL_CONFIGURATION_PATH = "/MySQL.properties";
    // </editor-fold>
}
