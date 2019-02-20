package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
