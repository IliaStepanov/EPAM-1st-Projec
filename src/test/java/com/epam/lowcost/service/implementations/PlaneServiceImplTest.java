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
        expectedList.add(new Plane(1, "Airbus A380",25,500,false));
        when(planeService.getAllPlanes()).thenReturn(expectedList);
        List<Plane> actualList = planeService.getAllPlanes();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getById() {
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
        planeService.deletePlane(1);
        verify(planeService).deletePlane(anyInt());
    }
}