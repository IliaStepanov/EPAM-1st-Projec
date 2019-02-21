package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.model.Plane;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAOImpl implements PlaneDAO {

    private DataSource dataSource;
    private RowMapper<Plane> planeRowMapper;


    public PlaneDAOImpl(DataSource dataSource, RowMapper<Plane> planeRowMapper) {

        this.dataSource = dataSource;
        this.planeRowMapper = planeRowMapper;
    }

    @Override
    public List<Plane> getAllPlanes() {
        List<Plane> allPlanes = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM PLANES WHERE isDeleted=false")) {
            while (rs.next()) {
                allPlanes.add(planeRowMapper.mapRow(rs, 1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlanes;
    }

    @Override
    public Plane getById(long planeId) {
        Plane plane = null;
        String sql = String.format("SELECT * FROM PLANES WHERE id=%d AND isDeleted=false", planeId);
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            if (rs.next()) {
                return planeRowMapper.mapRow(rs, 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    @Override
    public Plane addPlane(Plane plane) {
        String sql = String.format(
                "INSERT INTO PLANES (model, businessPlacesNumber, economPlacesNumber, isDeleted) " +
                        "VALUES ('%s', %d, %d, false)",
                plane.getModel(), plane.getBusinessPlacesNumber(), plane.getEconomPlacesNumber());
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()
        ) {
            int insert = stm.executeUpdate(sql);
            if (insert == 1) {
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
        if (getById(plane.getId()) == null) {
            return null;
        }
        String sql = String.format(
                "UPDATE PLANES SET model='%s',businessPlacesNumber=%d,economPlacesNumber=%d WHERE id=%d",
                plane.getModel(), plane.getBusinessPlacesNumber(), plane.getEconomPlacesNumber(), plane.getId());
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement()) {
            int update = stm.executeUpdate(sql);
            if (update == 1) {
                return plane;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }

    @Override
    public String deletePlane(long planeId) {
        String sql = String.format("UPDATE PLANES SET isDeleted=true WHERE id=%d", planeId);
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement()) {
            int update = stm.executeUpdate(sql);
            if (update == 1) {
                return String.format("Plane %d successfully deleted", planeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ("Plane was not deleted");
    }
}