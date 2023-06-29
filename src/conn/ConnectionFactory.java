package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        String username = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/notes";
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
