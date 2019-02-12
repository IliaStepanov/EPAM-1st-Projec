package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Plane;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlaneDAOImpl implements PlaneDAO {

    private DataSource dataSource;

    public PlaneDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Plane getPlaneById(Long id) { //need this before merging with Azamat's part of code
        Plane plane = new Plane();
        String sql = String.format("SELECT * FROM PLANES WHERE id = '%d'", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String model = rs.getString("model");
                Integer businessPlacesNumber = rs.getInt("businessPlacesNumber");
                Integer economPlacesNumber = rs.getInt("economPlacesNumber");
                plane = Plane.builder().id(id).model(model).businessPlacesNumber(businessPlacesNumber).
                        economPlacesNumber(economPlacesNumber).build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }
}
