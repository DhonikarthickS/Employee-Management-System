package db;
import java.sql.*;

// Compile : javac -cp ".;lib\mysql-connector-j-9.3.0.jar" -d out src\db\DBConnection.java src\model\*.java src\dao\*.java src\Main.java
// run     : java -cp ".;lib\mysql-connector-j-9.3.0.jar;out" Main

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "dhoni";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);

    }
}
