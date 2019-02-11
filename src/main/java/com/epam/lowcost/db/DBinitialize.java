package com.epam.lowcost.db;


import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DBinitialize {
    private DataSource dataSource;
    private Connection conn;
    private Statement stm;


    public DBinitialize(DataSource dataSource) {
        this.dataSource = dataSource;
        initiateDB();
    }


    private void initiateDB(){
        File fl = new File(this.getClass().getResource("/createTable").getFile());
        List<String> sqlBatch = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            stm = conn.createStatement();
            try (Stream<String> str = Files.lines(fl.toPath())) {
                str.forEach(sqlBatch::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                for (String s : sqlBatch) {
                    stm.addBatch(s);
                }
                stm.executeBatch();
                } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}



