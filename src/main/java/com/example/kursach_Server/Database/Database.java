package com.example.kursach_Server.Database;

import java.sql.*;

public class Database extends Config {
    Connection dbConnection = null;
    String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true";
    Statement statement;

    /*public void createDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            statement = dbConnection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }*/

    public void createDbConnection(String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, password);
            statement = dbConnection.createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер базы данных не найден: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    public ResultSet getData(String select) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(select);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void updateData(String select) {
        try {
            statement.executeUpdate(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}