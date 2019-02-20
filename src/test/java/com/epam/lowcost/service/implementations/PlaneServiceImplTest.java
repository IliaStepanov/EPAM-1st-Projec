package com.epam.lowcost.service.implementations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaneServiceImplTest {

    private PlaneService planeService = mock(PlaneService.class);

    @Test
    public void getAllPlanes() {
        List<Plane> expectedList = new ArrayList<>();
        List<Plane> actualList = planeService.getAllPlanes();

        when(planeService.getAllPlanes()).thenReturn(expectedList);

        assertEquals(expectedList, actualList);
    }

    @Test
    public void getById() {
        Plane expectedPlane = new Plane(1, "Airbus A380",25,500,false);
        Plane nullPlane = null;

        when(planeService.getById(1)).thenReturn(expectedPlane);
        when(planeService.getById(0)).thenReturn(null);
        when(planeService.getById(-1)).thenReturn(null);

        assertEquals(expectedPlane, planeService.getById(1));
        assertEquals(nullPlane, planeService.getById(0));
        assertEquals(nullPlane, planeService.getById(-1));
    }

    @Test
    public void addPlane() {
    }

    @Test
    public void updatePlane() {
    }

    @Test
    public void deletePlane() {
        planeService.deletePlane(1);
        planeService.deletePlane(0);
        planeService.deletePlane(-1);

        verify(planeService).deletePlane(1);
        verify(planeService).deletePlane(0);
        verify(planeService).deletePlane(-1);
    }
}