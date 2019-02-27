package com.epam.lowcost.service.implementations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.epam.lowcost.DAO.interfaces.PlaneDAO;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaneServiceImplTest {

    @Mock
    private PlaneDAO planeDAO;

    @InjectMocks
    private PlaneService planeService = new PlaneServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllPlanes() {
        List<Plane> expectedList = new ArrayList<>();

        when(planeDAO.getAllPlanes()).thenReturn(new ArrayList<Plane>());

        assertEquals(expectedList, planeService.getAllPlanes());
    }

    @Test
    public void getById_positiveIntegetNumberInRangeAsParam_PlaneEntetyReturned() {
        Plane expectedPlane = new Plane(1, "Airbus A380", 25, 500, false);

        when(planeDAO.getById(1)).thenReturn(expectedPlane);
        when(planeDAO.getById(1_000_000_000)).thenReturn(null);
        when(planeDAO.getById(0)).thenReturn(null);
        when(planeDAO.getById(-1)).thenReturn(null);

        assertEquals(expectedPlane, planeService.getById(1));
        assertNull(planeService.getById(1_000_000_000));
        assertNull(planeService.getById(0));
        assertNull(planeService.getById(-1));
    }

    @Test
    public void addPlane() {
        Plane plane = new Plane();

        planeService.addPlane(plane);

        verify(planeDAO).addPlane(plane);
    }

    @Test
    public void updatePlane() {
        Plane plane = new Plane();

        planeService.updatePlane(plane);

        verify(planeDAO).updatePlane(plane);
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