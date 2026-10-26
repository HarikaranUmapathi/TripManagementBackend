package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.DriverDTO;
import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import com.helloworld.tripmanagementsystem.Exception.ResourceNotFoundException;
import com.helloworld.tripmanagementsystem.Mapper.DriverMapper;
import com.helloworld.tripmanagementsystem.Repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

    @Mock
    private DriverRepository repo;

    @Mock
    private DriverMapper mapper;

    @InjectMocks
    private DriverService service;

    //Get Individual Driver Details

    @Test
    void testGetDriverById_Success() {

        DriverEntity entity = new DriverEntity();
        entity.setId(1L);
        entity.setDriverName("Hari");

        DriverDTO dto = new DriverDTO(1L, "Hari", 4, 987654321L);

        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDriverDTO(entity)).thenReturn(dto);

        ResponseEntity<DriverDTO> response = service.getIndividualDriverDetails(1);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Hari", response.getBody().getDriverName());

        verify(repo).findById(1);
        verify(mapper).toDriverDTO(entity);
    }

   //Get Individual Driver Details If Not Found

    @Test
    void testGetDriverById_NotFound() {

        when(repo.findById(5)).thenReturn(Optional.empty());

        ResponseEntity<DriverDTO> response = service.getIndividualDriverDetails(5);

        assertEquals(404, response.getStatusCode().value());

        verify(repo).findById(5);
    }

   //Get All Driver Details

    @Test
    void testGetAllDrivers() {

        DriverEntity entity = new DriverEntity();
        entity.setId(1L);

        DriverDTO dto = new DriverDTO(1L, "Hari", 4, 987654321L);

        when(repo.findAll()).thenReturn(List.of(entity));
        when(mapper.toDriverDTO(entity)).thenReturn(dto);

        List<DriverDTO> result = service.getAllDriverDetails();

        assertEquals(1, result.size());
        assertEquals("Hari", result.get(0).getDriverName());
    }

   //Create The Driver Details

    @Test
    void testCreateDriver() {

        DriverDTO input = new DriverDTO(0L, "Hari", 4, 99999999L);

        DriverEntity entity = new DriverEntity();
        DriverEntity saved = new DriverEntity();
        saved.setId(1L);

        DriverDTO output = new DriverDTO(1L, "Hari", 4, 99999999L);

        when(mapper.toDriverEntity(input)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(saved);
        when(mapper.toDriverDTO(saved)).thenReturn(output);

        DriverDTO result = service.createDriverDetails(input);

        assertEquals(1, result.getId());

        verify(repo).save(entity);
    }

    //Update the Driver Details

    @Test
    void testUpdateDriver_Success() {

        DriverEntity entity = new DriverEntity();
        entity.setId(1L);

        DriverDTO updateDto = new DriverDTO(1L, "Updated", 5, 88888888L);

        DriverEntity saved = new DriverEntity();
        saved.setId(1L);

        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(repo.save(entity)).thenReturn(saved);
        when(mapper.toDriverDTO(saved)).thenReturn(updateDto);

        DriverDTO result = service.updateDriverDetails(1, updateDto);

        assertEquals("Updated", result.getDriverName());
        verify(repo).save(entity);
    }

  //Update the Driver if the Id is not Found

    @Test
    void testUpdateDriver_NotFound() {

        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.updateDriverDetails(99, new DriverDTO());
        });
    }
}