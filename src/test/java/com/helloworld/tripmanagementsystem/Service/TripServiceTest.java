package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.TripDTO;
import com.helloworld.tripmanagementsystem.Entity.TripEntity;
import com.helloworld.tripmanagementsystem.Exception.ResourceNotFoundException;
import com.helloworld.tripmanagementsystem.Mapper.TripMapper;
import com.helloworld.tripmanagementsystem.Repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripServiceTest {

    @Mock
    private TripRepository repo;

    @Mock
    private TripMapper mapper;

    @InjectMocks
    private TripService service;

    private TripEntity entity;
    private TripDTO dto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        entity = new TripEntity();
        entity.setTripId(1L);
        entity.setBookedBy("Hari");

        dto = new TripDTO();
        dto.setTripId(1);
        dto.setBookedBy("Hari");
    }

    // Get Individual Trip
    @Test
    void testGetTripById_Found() {

        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toTripDTO(entity)).thenReturn(dto);

        ResponseEntity<TripDTO> response = service.getIndividualTripDetails(1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Hari", response.getBody().getBookedBy());
    }

   //Get Individual Trip- Negative Scenario
    @Test
    void testGetTripById_NotFound() {

        when(repo.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<TripDTO> response = service.getIndividualTripDetails(1);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    //Get All Trip
    @Test
    void testGetAllTrips() {

        when(repo.findAll()).thenReturn(List.of(entity));
        when(mapper.toTripDTO(entity)).thenReturn(dto);

        List<TripDTO> result = service.getAllTripDetails();

        assertEquals(1, result.size());
        assertEquals("Hari", result.get(0).getBookedBy());
    }

   //Create Trip
    @Test
    void testCreateTrip() {

        when(mapper.toTripEntity(dto)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toTripDTO(entity)).thenReturn(dto);

        TripDTO result = service.createTripDetails(dto);

        assertEquals("Hari", result.getBookedBy());
        verify(repo).save(entity);
    }

    //Update Trip
    @Test
    void testUpdateTrip_Success() {

        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toTripDTO(entity)).thenReturn(dto);

        TripDTO result = service.updateTripDetails(1, dto);

        assertEquals("Hari", result.getBookedBy());
        verify(repo).save(entity);
    }

   //Update Trip - Negative Scenario
    @Test
    void testUpdateTrip_NotFound() {

        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.updateTripDetails(1, dto);
        });
    }

    //Soft Delete
    @Test
    void testSoftDeleteTrip_Success() {

        when(repo.findById(1)).thenReturn(Optional.of(entity));

        String result = service.softDeleteTripDetails(1);

        assertEquals("Successfully Soft Deleted", result);
        verify(repo).save(entity);
    }

   //Soft Delete -Negative Scenario
    @Test
    void testSoftDeleteTrip_NotFound() {

        when(repo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.softDeleteTripDetails(1);
        });
    }
}