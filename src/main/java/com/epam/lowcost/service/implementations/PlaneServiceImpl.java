package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private PlaneDAO planeDAO;

//    public PlaneServiceImpl(PlaneDAO planeDAO) {
//        this.planeDAO = planeDAO;
//    }

    @Override
    public List<Plane> getAllPlanes() {
        return planeDAO.getAllPlanes();
    }

    @Override
    public Plane getById(long planeId) {
        return planeDAO.getById(planeId);
    }

    @Override
    public Plane addPlane(Plane plane) {
        return planeDAO.addPlane(plane);
    }

    @Override
    public Plane updatePlane(Plane plane) {
        return planeDAO.updatePlane(plane);
    }

    @Override
    public String deletePlane(long planeId) {
        return planeDAO.deletePlane(planeId);
    }

    @Override
    public Map<String, Object> getPlanesByPage(int pageId, int numberOfPlanesOnPage) {
        if (pageId <= 0) {
            pageId = 1;
        }
        int users = planeDAO.countPlanes();
        int pagesNum;
        if (users % numberOfPlanesOnPage != 0) {
            pagesNum = (users / numberOfPlanesOnPage + 1);
        } else {
            pagesNum = (users / numberOfPlanesOnPage);
        }
        if (pageId >= pagesNum) {
            pageId = pagesNum;
        }

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("planes", planeDAO.getPlanesByPage(pageId, numberOfPlanesOnPage));
        pageInfo.put("pagesNum", pagesNum);
        pageInfo.put("pageId", pageId);

        return pageInfo;
    }

    @Override
    public int countPlanes() {
        return planeDAO.countPlanes();
    }
}
