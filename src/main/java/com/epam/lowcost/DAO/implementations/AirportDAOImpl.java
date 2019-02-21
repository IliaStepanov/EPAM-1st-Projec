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
    public Airport getAirportByCode(String iataCode) {
        Airport airport = null;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = "SELECT * FROM AIRPORTS WHERE iataCode = ?";
            airport = jdbcTemplate.queryForObject(query, airportRowMapper, iataCode);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return airport;
    }

    @Override
    public Airport addNewAirport(Airport airport) {
        String iataCode = airport.getCode();
        String nameRus = airport.getNameRus();
        String cityEng = airport.getCityEng();
        String cityRus = airport.getCityRus();
        String nameEng = airport.getNameEng();
        String countryEng = airport.getCountryEng();
        String countryRus = airport.getCountryRus();

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String query = "INSERT  INTO AIRPORTS (iataCode,nameRus, cityEng, cityRus, nameEng,countryEng,countryRus) " +
                    "VALUES (?,?,?,?,?,?,?)";
            airport = jdbcTemplate.queryForObject(query, airportRowMapper, iataCode,
                    nameRus, cityEng, cityRus, nameEng, countryEng, countryRus);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return airport;
    }

//    @Override
//    public Airport deleteAirport(Long id) {
//        return null;
//    }

    @Override
    public Airport updateAirport(Airport airport) {
        String iataCode = airport.getCode();
        String nameRus = airport.getNameRus();
        String cityEng = airport.getCityEng();
        String cityRus = airport.getCityRus();
        String nameEng = airport.getNameEng();
        String countryEng = airport.getCountryEng();
        String countryRus = airport.getCountryRus();
        String query = "UPDATE AIRPORTS SET nameRus = ?, cityEng = ?, cityRus = ?, nameEng = ?," +
                " countryEng = ?, countryRus = ? WHERE iataCode = ?";
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            int lines = jdbcTemplate.update(query, nameRus, cityEng, cityRus, nameEng, countryEng, countryRus,iataCode);
            if (lines==1) return  airport;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
