package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.AirportDAO;
import com.epam.lowcost.model.Airport;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class AirportDAOImpl implements AirportDAO {
    private DataSource dataSource;
    private RowMapper<Airport> airportRowMapper;

    public AirportDAOImpl(DataSource dataSource, RowMapper<Airport> airportRowMapper) {
        this.dataSource = dataSource;
        this.airportRowMapper = airportRowMapper;
    }

    @Override
    public List<Airport> getAllAirports() {
        List<Airport> airports = null;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = "SELECT * FROM AIRPORTS";
            airports = jdbcTemplate.query(query, airportRowMapper);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return airports;
    }

    @Override
    public Airport getAirportByCode(String code) {
        Airport airport = null;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = "SELECT * FROM AIRPORTS WHERE code = ?";
            airport = jdbcTemplate.queryForObject(query, airportRowMapper, code);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return airport;
    }

    @Override
    public Airport addNewAirport(Airport airport) {
        int lines = 0;
        String code = airport.getCode();
        String nameRus = airport.getNameRus();
        String cityEng = airport.getCityEng();
        String cityRus = airport.getCityRus();
        String nameEng = airport.getNameEng();
        String countryEng = airport.getCountryEng();
        String countryRus = airport.getCountryRus();


        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = "INSERT  INTO AIRPORTS (code,nameRus, cityEng, cityRus, nameEng,countryEng,countryRus) " +
                    "VALUES (?,?,?,?,?,?,?)";
            lines = jdbcTemplate.update(query, code,
                    nameRus, cityEng, cityRus, nameEng, countryEng, countryRus);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        if (lines != 1)
            airport = null;
        return airport;
    }

//    @Override
//    public Airport deleteAirport(Long id) {
//        return null;
//    }

    @Override
    public Airport updateAirport(Airport airport) {
        String code = airport.getCode();
        String nameRus = airport.getNameRus();
        String cityEng = airport.getCityEng();
        String cityRus = airport.getCityRus();
        String nameEng = airport.getNameEng();
        String countryEng = airport.getCountryEng();
        String countryRus = airport.getCountryRus();
        String query = "UPDATE AIRPORTS SET nameRus = ?, cityEng = ?, cityRus = ?, nameEng = ?," +
                " countryEng = ?, countryRus = ? WHERE code = ?";
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            int lines = jdbcTemplate.update(query, nameRus, cityEng, cityRus, nameEng, countryEng, countryRus, code);
            if (lines == 1) return airport;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Airport> getAirportsByPage(int pageId, int numberOfAirportsOnPage) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        pageId = pageId - 1;
        if (pageId > 0) {
            pageId = pageId * numberOfAirportsOnPage;
        }

        return jdbcTemplate.query("SELECT * FROM AIRPORTS LIMIT " + (pageId) + "," + numberOfAirportsOnPage, airportRowMapper);
    }

    @Override
    public int countAirports() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT COUNT(code) FROM AIRPORTS", Integer.class);
    }
}
