package com.epam.lowcost.service;

import com.epam.lowcost.DAO.PlaneDAO;
import com.epam.lowcost.model.Plane;

import java.util.List;

public class PlaneServiceImpl implements PlaneService {

    private PlaneDAO planeDAO;

    public PlaneServiceImpl(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

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
