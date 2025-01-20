package common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.cdimascio.dotenv.Dotenv;

public class DBUtils
{

    private static final Logger LOGGER = Logger.getLogger(DBUtils.class.getName());
    private static final Dotenv DOT_ENV = Dotenv.load();

    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Connection conn = null;
        try
        {
            String driver = DOT_ENV.get("DB_DRIVER");
            String url = DOT_ENV.get("DB_URL");
            String username = DOT_ENV.get("DB_USERNAME");
            String password = DOT_ENV.get("DB_PASSWORD");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            LOGGER.info("Database connection successfully established.");
        } catch (ClassNotFoundException e)
        {
            LOGGER.log(Level.SEVERE, "SQL Driver not found", e);
            throw e;
        } catch (SQLException e)
        {
            LOGGER.log(Level.SEVERE, "Failed to create database connection", e);
            throw e;
        }
        return conn;
    }
}
