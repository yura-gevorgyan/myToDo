package am.itspace.mytodo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static DBConnectionProvider provider = null;
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/my_to_do";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public static DBConnectionProvider getInstance() {
        if (provider == null) {
            return new DBConnectionProvider();
        }
        return provider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
