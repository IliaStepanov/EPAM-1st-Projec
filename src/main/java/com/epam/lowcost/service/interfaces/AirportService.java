package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.Airport;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface AirportService {

    List<Airport> getAllAirports();

    Airport getAirportByCode(String code);

    Airport addNewAirport(Map<String, String> params);

    // Airport deleteAirport(Long id);

    Airport updateAirport(Map<String, String> params);
}
