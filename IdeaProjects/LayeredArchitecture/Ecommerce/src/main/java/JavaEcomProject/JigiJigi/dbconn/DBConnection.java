package JavaEcomProject.JigiJigi.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=EcomDB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa"; // your SQL username
    private static final String PASSWORD = "sqlserver"; // your SQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
