package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.AirportDAO;
import com.epam.lowcost.model.Airport;
import com.epam.lowcost.service.interfaces.AirportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportServiceImpl implements AirportService {
    AirportDAO airportDAO;

    public AirportServiceImpl(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportDAO.getAllAirports();
    }

    @Override
    public Airport getAirportByCode(String code) {
        return airportDAO.getAirportByCode(code);
    }

    @Override
    public Airport addNewAirport(Map<String, String> params) {
        Airport airport = airportDAO.addNewAirport(
                Airport.builder()
                        .cityEng(params.get("cityEng"))
                        .cityRus(params.get("cityRus"))
                        .code(params.get("code").toUpperCase())
                        .countryEng(params.get("countryEng"))
                        .countryRus(params.get("countryRus"))
                        .nameEng(params.get("nameEng"))
                        .nameRus(params.get("nameRus"))
                        .build()
        );
        return airport;

    }

    @Override
    public Airport updateAirport(Map<String, String> params) {
        Airport airport = airportDAO.updateAirport(
                Airport.builder()
                        .cityEng(params.get("cityEng"))
                        .cityRus(params.get("cityRus"))
                        .code(params.get("code").toUpperCase())
                        .countryEng(params.get("countryEng"))
                        .countryRus(params.get("countryRus"))
                        .nameEng(params.get("nameEng"))
                        .nameRus(params.get("nameRus"))
                        .build()
        );
        return airport;
    }

    @Override
    public Map<String, Object> getAirportsByPage(int pageId, int numberOfAirportsOnPage) {
        if (pageId <= 0) {
            pageId = 1;
        }
        int users = airportDAO.countAirports();
        int pagesNum;
        if (users % numberOfAirportsOnPage != 0) {
            pagesNum = (users / numberOfAirportsOnPage + 1);
        } else {
            pagesNum = (users / numberOfAirportsOnPage);
        }
        if (pageId >= pagesNum) {
            pageId = pagesNum;
        }

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("airports", airportDAO.getAirportsByPage(pageId, numberOfAirportsOnPage));
        pageInfo.put("pagesNum", pagesNum);
        pageInfo.put("pageId", pageId);

        return pageInfo;
    }

    @Override
    public int countAirports() {
        return airportDAO.countAirports();
    }
}
