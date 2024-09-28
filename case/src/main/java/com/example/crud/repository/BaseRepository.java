package com.example.crud.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Kết nối DB // đổi lại thành connectionMysql
public class BaseRepository {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/c0324m4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ngoclinh29";

    public BaseRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("kết nối thành công");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
