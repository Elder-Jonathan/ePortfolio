package cpsc.au.cpsc5130.hospitalmanagementapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection = null;
    private static boolean connected = false;

    /**
     * Attempt to connect with the given dbName, user, pass.
     * For your scenario, the URL might be
     * "jdbc:mysql://sysmysql8.auburn.edu:3306/" + dbName
     */
    public static boolean connect(String dbName, String user, String pass) {
        try {
            String url = "jdbc:mysql://sysmysql8.auburn.edu:3306/" + dbName;
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected successfully.");
            connected = true;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            connected = false;
            return false;
        }
    }

    public static boolean isConnected() {
        return connected;
    }

    public static Connection getConnection() throws SQLException {
        if (connected && connection != null && !connection.isClosed()) {
            return connection;
        } else {
            throw new SQLException("Not connected to database or connection is closed.");
        }
    }

    // Note: Do not close the connection in individual controllers.
// The connection is closed only via handleLogout() in MainController.
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connected = false;
        }
    }
}