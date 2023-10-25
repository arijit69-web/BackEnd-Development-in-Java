import java.sql.*;

// Singleton class - Factory Method
public class ConnectionProvider {

    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/jdbc", "root", "PASSWORD");

                if (conn.isClosed()) {

                    System.out.println("Connection Not Established");

                } else {
                    System.out.println("Connection Established");

                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return conn;
    }
}
