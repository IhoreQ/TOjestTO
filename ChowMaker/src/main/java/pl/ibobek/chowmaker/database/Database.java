package pl.ibobek.chowmaker.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database database = null;

    private String URL = "jdbc:postgresql://localhost:5432/chowmaker";
    private String USER = "postgres";
    private String PASSWORD = "humbucker123";

    private Connection connection = null;

    public Database() {
        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

}
