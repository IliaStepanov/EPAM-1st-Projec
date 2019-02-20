package com.epam.lowcost.service.implementations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.junit.Test;

public class PlaneServiceImplTest {


    @Test
    public void getAllPlanes() {
    }

    @Test
    public void getById() {
        PlaneService planeService = mock(PlaneServiceImpl.class);
        Plane expectedPlane = new Plane(1, "Airbus A380",25,500,false);
        when(planeService.getById(1)).thenReturn(expectedPlane);
        Plane actualPlane = planeService.getById(1);
        assertEquals(actualPlane, expectedPlane);
    }

    @Test
    public void addPlane() {
    }

    @Test
    public void updatePlane() {
    }

    @Test
    public void deletePlane() {
    }
}