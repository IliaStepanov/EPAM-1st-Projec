package com.epam.lowcost.db;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBinitialize {
    BasicDataSource basicDataSource;
    Connection conn;
    Statement stm;

    public DBinitialize(BasicDataSource basicDataSource) throws SQLException {
        this.basicDataSource = basicDataSource;
        conn = basicDataSource.getConnection();
        stm = conn.createStatement();
        createTable();
        populateTable();
        selectFromTable();

    }



    public void createTable() throws SQLException {

        String sql =  "CREATE TABLE   REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        stm.executeUpdate(sql);

    }
    public void populateTable() throws SQLException{
        String sql = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)";

        stm.executeUpdate(sql);
        sql = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";

        stm.executeUpdate(sql);
        sql = "INSERT INTO Registration " + "VALUES (102, 'Zaid', 'Khan', 30)";

        stm.executeUpdate(sql);
        sql = "INSERT INTO Registration " + "VALUES(103, 'Sumit', 'Mittal', 28)";

        stm.executeUpdate(sql);
        System.out.println("Inserted records into the table...");

    }


    private void selectFromTable() throws SQLException {
        String sql = "SELECT * from REGISTRATION";
        ResultSet resultSet = stm.executeQuery(sql);
            while (resultSet.next())
            {
                int id  = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String first = resultSet.getString("first");
                String last = resultSet.getString("last");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);

            }


    }

}



