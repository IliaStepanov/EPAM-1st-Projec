package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.exceptions.DatabaseErrorException;
import com.epam.lowcost.model.AbstractJdbcModel;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAOImpl<T extends AbstractJdbcModel> {
    DataSource dataSource;
    RowMapper<T> rowMapper;

    public AbstractDAOImpl(DataSource dataSource, RowMapper<T> rowMapper) {
        this.dataSource = dataSource;
        this.rowMapper = rowMapper;
    }

    List<T> executeSqlSelect(String sql) throws DatabaseErrorException {
        List<T> result = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
        return result;
    }

    T executeSqlInsert(T model, String sql) throws DatabaseErrorException {
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()) {
            int insert = stm.executeUpdate(sql);
            if (insert == 1) {
                try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        model.setId(generatedKeys.getLong(1));
                    } else {
                        throw new DatabaseErrorException();
                    }
                }
                return model;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
    }

    int executeSqlUpdate(String sql) throws DatabaseErrorException {
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement()) {
            return stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException();
        }
    }
}
