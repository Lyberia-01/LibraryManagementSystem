package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "postgresql://postgres:dreamdevs@db.ajzhsrrjegetbmyuvtma.supabase.co:5432/postgres";
    private static final String USER = "dreamdevs";
    private static final String PASSWORD = "dreamdevs";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}