package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.Airport;

import java.util.List;

public interface AirportService {

    List<Airport> getAllAirports();

    Airport getAirportByCode(String code);

    Airport addNewAirport(Airport airport);

    // Airport deleteAirport(Long id);

    Airport updateAirport(Airport airport);
}
