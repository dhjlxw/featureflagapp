package com.example.demo.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInspector {
    private static final String URL = "jdbc:mysql://localhost/app";
    private static final String USER = "dhj";
    private static final String PASSWORD = "pwd;<>P&*()123";

    public static List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             ResultSet rs = conn.getMetaData().getTables(null, null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                tables.add(rs.getString("TABLE_NAME"));
            }
        }
        return tables;
    }

    public static String getTableStructure(String tableName) throws SQLException {
        StringBuilder structure = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             ResultSet rs = conn.createStatement().executeQuery("DESCRIBE " + tableName)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    structure.append(metaData.getColumnName(i)).append(": ").append(rs.getString(i));
                    if (i < columnCount) structure.append(", ");
                }
                structure.append("\n");
            }
        }
        return structure.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println("Tables in database:");
            for (String table : getTables()) {
                System.out.println(table);
                System.out.println("Structure:");
                System.out.println(getTableStructure(table));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
