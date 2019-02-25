package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.exceptions.DatabaseErrorException;
import com.epam.lowcost.model.Plane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class PlaneDAOImpl extends AbstractDAOImpl<Plane> implements PlaneDAO {

    public PlaneDAOImpl(DataSource dataSource, RowMapper<Plane> planeRowMapper) {
        super(dataSource, planeRowMapper);
    }

    @Override
    public List<Plane> getAllPlanes() throws DatabaseErrorException {
        String sql = "SELECT * FROM PLANES WHERE isDeleted=false";
        return executeSqlSelect(sql);
    }

    @Override
    public Plane getById(long planeId) throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM PLANES WHERE id=%d AND isDeleted=false", planeId);
        return executeSqlSelect(sql).get(0);
    }

    @Override
    public Plane addPlane(Plane plane) throws DatabaseErrorException {
        String sql = String.format(
                "INSERT INTO PLANES (model, businessPlacesNumber, economPlacesNumber, isDeleted) " +
                        "VALUES ('%s', %d, %d, false)",
                plane.getModel(), plane.getBusinessPlacesNumber(), plane.getEconomPlacesNumber());
        return executeSqlInsert(plane, sql);
    }

    @Override
    public Plane updatePlane(Plane plane) throws DatabaseErrorException {
        String sql = String.format(
                "UPDATE PLANES SET model='%s',businessPlacesNumber=%d,economPlacesNumber=%d WHERE id=%d",
                plane.getModel(), plane.getBusinessPlacesNumber(), plane.getEconomPlacesNumber(), plane.getId());
        return executeSqlUpdate(sql) == 1 ? plane : null;
    }

    @Override
    public String deletePlane(long planeId) throws DatabaseErrorException {
        String sql = String.format("UPDATE PLANES SET isDeleted=true WHERE id=%d", planeId);
        return executeSqlUpdate(sql) == 1 ? String.format("UPDATE PLANES SET isDeleted=true WHERE id=%d", planeId)
                : "Plane was not deleted";
    }

    @Override
    public List<Plane> getPlanesByPage(int pageId, int numberOfPlanesOnPage) {
        pageId = pageId - 1;
        if (pageId > 0) {
            pageId = pageId * numberOfPlanesOnPage;
        }
        return executeSqlSelect("SELECT * FROM PLANES WHERE isDeleted=false LIMIT " + (pageId) + "," + numberOfPlanesOnPage);
    }

    @Override
    public int countPlanes() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM PLANES WHERE isDeleted=false", Integer.class);
    }
}