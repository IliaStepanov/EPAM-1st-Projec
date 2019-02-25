package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.Plane;

import java.util.List;
import java.util.Map;

public interface PlaneService {

    List<Plane> getAllPlanes();

    Plane getById(long planeId);

    Plane addPlane(Plane plane);

    Plane updatePlane(Plane plane);

    String deletePlane(long planeId);

    Map<String,Object> getPlanesByPage(int pageId, int numberOfPlanesByPage);

    int countPlanes();
}
