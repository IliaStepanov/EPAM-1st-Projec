package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.util.PlaneRowMapper;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlaneDAOImplTest {

    private DataSource dataSource = mock(DataSource.class);
    private RowMapper<Plane> rowMapper = mock(PlaneRowMapper.class);
    private PlaneDAO planeDAO = new PlaneDAOImpl(dataSource, rowMapper);

    @Test
    public void getAllPlanes() {
    }

    @Test
    public void getById() throws SQLException {
        Plane expectedPlane = new Plane(1, "Airbus A380",25,500,false);
        Plane nullPlane = null;

        when(dataSource.getConnection());

        assertEquals(expectedPlane, planeDAO.getById(1));
        assertEquals(nullPlane, planeDAO.getById(0));
        assertEquals(nullPlane, planeDAO.getById(-1));
    }

    @Test
    public void addPlane() {
    }

    @Test
    public void updatePlane() {
    }

    @Test
    public void deletePlane() {
    }
}