package ru.kurma.util;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector instance;

    private final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private final String USER = "postgres";
    private final String PASSWORD = "041014";
    private final String CLASSNAME = "org.postgresql.Driver";

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return instance == null ? instance = new DBConnector() : instance;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(CLASSNAME).newInstance());
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
