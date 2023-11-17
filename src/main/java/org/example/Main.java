package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String dbURL = "jdbc:postgresql://localhost:5432/univer_small";
    private static final String username = "student";
    private static final String password = "student";

    public static void main(String[] args) {
        /*Connection conn = null;
        try {
             conn = DriverManager.getConnection(dbURL, username, password);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }*/

        try (
                Connection conn = DriverManager.getConnection(dbURL, username, password);
                Statement stmt = conn.createStatement();
                Scanner scanner = new Scanner(System.in)
        ) {

            System.out.println("Connected to Database");
            String updQuery = "insert into instructor (id,name,dept_name, salary) " +
                    "values ('11225','Sezim','Biology',50000)";
//            int numOfRows = stmt.executeUpdate(updQuery);
//            System.out.printf("Updated rows: %d%n", numOfRows);
            String slctQuery = "select * from instructor where name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(slctQuery);

            preparedStatement.setString(1,scanner.nextLine());
            ResultSet rs = preparedStatement.executeQuery();
//            ResultSet rs = stmt.executeQuery(slctQuery);

            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                System.out.printf("%-15s", metaData.getColumnName(i + 1));
            }
            System.out.println();
            while (rs.next()) {
                System.out.printf("%-15s%-15s%-15s%.2f%n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}