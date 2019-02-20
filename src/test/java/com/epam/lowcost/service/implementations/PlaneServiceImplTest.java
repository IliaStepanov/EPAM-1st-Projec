package com.epam.lowcost.service.implementations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaneServiceImplTest {

    private PlaneDAO planeDAO = mock(PlaneDAO.class);
    private PlaneService planeService = new PlaneServiceImpl(planeDAO);

    @Test
    public void getAllPlanes() {
    }

    @Test
    public void getById_positiveIntegetNumberInRangeAsParam_PlaneEntetyReturned() {
        Plane expectedPlane = new Plane(1, "Airbus A380",25,500,false);

        when(planeDAO.getById(1)).thenReturn(expectedPlane);

        assertEquals(expectedPlane, planeService.getById(1));
    }

    @Test
    public void getById_positiveIntegetNumberOutOfRangeAsParam_nullReturned() {
        Plane expectedPlane = null;

        when(planeDAO.getById(1_000_000_000)).thenReturn(null);

        assertEquals(expectedPlane, planeService.getById(1_000_000_000));
    }

    @Test
    public void getById_zeroAsParam_nullReturned() {
        Plane expectedPlane = null;

        when(planeDAO.getById(0)).thenReturn(null);

        assertEquals(expectedPlane, planeService.getById(0));
    }

@Test
    public void getById_negativeIntegerNumberAsParam_nullReturned() {
        Plane expectedPlane = null;

        when(planeDAO.getById(-1)).thenReturn(null);

        assertEquals(expectedPlane, planeService.getById(-1));
    }

    @Test
    public void addPlane() {
        Plane expectedPlane = new Plane(1, "Airbus A380",25,500,false);
    }

    @Test
    public void updatePlane() {
    }

    @Test
    public void deletePlane() {
        planeService.deletePlane(1);
        planeService.deletePlane(0);
        planeService.deletePlane(-1);

        verify(planeDAO).deletePlane(1);
        verify(planeDAO).deletePlane(0);
        verify(planeDAO).deletePlane(-1);
    }
}