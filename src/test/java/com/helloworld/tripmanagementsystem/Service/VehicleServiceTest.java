package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.VehicleDTO;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import com.helloworld.tripmanagementsystem.Mapper.VehicleMapper;
import com.helloworld.tripmanagementsystem.Repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

        @Mock
        VehicleRepository repo;

        @Mock
        VehicleMapper mapper;

        @InjectMocks
        VehicleService service;

        VehicleEntity entity;
        VehicleDTO dto;

        @BeforeEach
        void setup() {
            entity = new VehicleEntity();
            entity.setVehicleId(1L);
            entity.setVehicleType("Bus");
            entity.setRegistrationNumber(12345L);
            entity.setInsured(true);

            dto = new VehicleDTO(1L, "Bus", 12345L, true);
        }
        //Get All Vehicle

        @Test
        void testGetAllVehicles() {

            when(repo.findAll()).thenReturn(List.of(entity));
            when(mapper.toVehicleDTO(entity)).thenReturn(dto);

            List<VehicleDTO> result = service.getAllVehicleDetails();

            assertEquals(1, result.size());
            assertEquals("Bus", result.get(0).getVehicleType());

            verify(repo,times(1)).findAll();
        }

    //Get Individual Vehicle
    @Test
    void testGetVehicleById_Found() {

        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toVehicleDTO(entity)).thenReturn(dto);

        ResponseEntity<VehicleDTO> response = service.getIndividualVehicleDetails(1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Bus", response.getBody().getVehicleType());
    }

    //Get Individual Vehicle - Negative Scenario
    @Test
    void testGetVehicleById_NotFound() {

        when(repo.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<VehicleDTO> response = service.getIndividualVehicleDetails(1);

        assertEquals(404, response.getStatusCode().value());
    }

    //Create Vehicle Details

    @Test
    void testCreateVehicle() {

        when(mapper.toVehicleEntity(dto)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toVehicleDTO(entity)).thenReturn(dto);

        VehicleDTO result = service.createVehicleDetails(dto);

        assertEquals("Bus", result.getVehicleType());

        verify(repo, times(1)).save(entity);
    }

    //Update Vehicle Details
    @Test
    void testUpdateVehicleSuccess() {

        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toVehicleDTO(entity)).thenReturn(dto);

        VehicleDTO result = service.updateVehicleDetails(1, dto);

        assertEquals("Bus", result.getVehicleType());

        verify(repo).save(entity);
    }

    //Update Vehicle Details - Negative Scenario
}
