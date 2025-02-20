package common.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils
{
    private DBUtils()
    {
    }

    private static final Dotenv DOT_ENV = Dotenv.configure()
            .directory(Paths.get("D:/F_CODE/CrewBE/BE3/Task-Management-Application").toAbsolutePath().toString())
            .load();

    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String driver = DOT_ENV.get("DB_DRIVER");
        String url = DOT_ENV.get("DB_URL");
        String username = DOT_ENV.get("DB_USERNAME");
        String password = DOT_ENV.get("DB_PASSWORD");
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
}
