package com.epam.lowcost.DAO.interfaces;

import com.epam.lowcost.model.Airport;

import java.util.List;

public interface AirportDAO {
    List<Airport> getAllAirports();

    Airport getAirportByCode(String code);

    Airport addNewAirport(Airport airport);

   // Airport deleteAirport(Long id);

    Airport updateAirport(Airport airport);

    List<Airport> getAirportsByPage(int pageId, int numberOfAirportsOnPage);

    int countAirports();

}
