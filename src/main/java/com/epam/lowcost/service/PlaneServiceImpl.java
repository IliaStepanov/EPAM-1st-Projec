package com.epam.lowcost.service;

import com.epam.lowcost.DAO.PlaneDAO;
import com.epam.lowcost.model.Plane;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlaneServiceImpl implements PlaneService {
    PlaneDAO planeDAO;

    @Override
    public Plane getPlaneById(Long id) {
        return planeDAO.getPlaneById(id);
    }
}
