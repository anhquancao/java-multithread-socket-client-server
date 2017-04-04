package database;

import utils.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by caoquan on 4/4/17.
 */
public class SQLiteJDBCDriverConnection {
    private static SQLiteJDBCDriverConnection sqLiteJDBCDriverConnection = new SQLiteJDBCDriverConnection();
    private Connection connection;

    private SQLiteJDBCDriverConnection() {
        try {
            connection = DriverManager.getConnection(Constant.DATABASE_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SQLiteJDBCDriverConnection getInstance() {
        return sqLiteJDBCDriverConnection;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
