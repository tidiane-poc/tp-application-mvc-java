package ma.enset.iidbcc.tpapplicationmvc.dao;

import java.sql.Connection;

public class JdbcConnection {
    private static Connection instance;
    private static final String URL = "jdbc:mysql://localhost:3306/db_patients";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "NO_PASSWORD";

    // private constructor to prevent instantiation
    private JdbcConnection() {
    }

    // static statement block is executed when the class is loaded into memory
    static {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver"); not needed since JDBC 4.0 automatically loads the driver
            instance = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        return instance;
    }
}
