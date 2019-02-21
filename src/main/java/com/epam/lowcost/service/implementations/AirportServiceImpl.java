package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.AirportDAO;
import com.epam.lowcost.model.Airport;
import com.epam.lowcost.service.interfaces.AirportService;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    AirportDAO airportDAO;

    @Override
    public List<Airport> getAllAirports() {
        return  null;
    }

    @Override
    public Airport getAirportByCode(String iataCode) {
        return null;
    }

    @Override
    public Airport addNewAirport(Airport airport) {
        return null;
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return null;
    }
}
