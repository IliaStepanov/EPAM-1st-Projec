package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Plane;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAOImpl implements PlaneDAO{

    private DataSource dataSource;

    public PlaneDAOImpl(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public List<Plane> getAllPlanes() {
        List<Plane> allPlanes = new ArrayList<>();
        Plane plane;
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM PLANES")) {
            while (rs.next()) {

                long id = rs.getLong("id");
                String model = rs.getString("model");
                int businessPlacesNumber = rs.getInt("businessPlacesNumber");
                int economPlaces = rs.getInt("economPlacesNumber");

                plane = new Plane(id, model, businessPlacesNumber, economPlaces);

                allPlanes.add(plane);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlanes;
    }

    @Override
    public Plane getById(long planeId) {
        String sql = String.format("SELECT * FROM PLANES WHERE id='%d'", planeId);
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            if (rs.next()) {

                long id = rs.getLong("id");
                String model = rs.getString("model");
                int businessPlacesNumber = rs.getInt("businessPlacesNumber");
                int economPlaces = rs.getInt("economPlacesNumber");

                return new Plane(id, model, businessPlacesNumber, economPlaces);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Plane addPlane(Plane plane) {
        String sql = String.format(
                "INSERT INTO PLANES (model, businessPlacesNumber, economPlacesNumber, " +
                        "VALUES ('%s', %d, %d)",

                plane.getModel(), plane.getBusinessPlacesNumber(), plane.getEconomPlacesNumber());

        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()
        ) {
            int insert = stm.executeUpdate(sql);
            if (insert == 1){
                ResultSet rs = stm.executeQuery("SELECT * FROM PLANES");
                rs.last();
                long newId = rs.getLong("id");
                plane.setId(newId);
                return plane;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        plane = null;
        return plane;
    }

    @Override
    public Plane updatePlane(Plane plane) {
        return null;
    }
}
