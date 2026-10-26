package com.helloworld.tripmanagementsystem.Controller;

import com.helloworld.tripmanagementsystem.DTO.VehicleDTO;
import com.helloworld.tripmanagementsystem.Service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(controllers = VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VehicleService vService;

    @Autowired
    private ObjectMapper objectMapper;

    // Get Single Vehicle Details
    @Test
    void getVehicleDetails() throws Exception {

        VehicleDTO dto = new VehicleDTO(1L, "Bus", 123456789L, true);

        when(vService.getIndividualVehicleDetails(1))
                .thenReturn(ResponseEntity.ok(dto));

        mockMvc.perform(get("/vehicle/getvehicle/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleId").value(1L))
                .andExpect(jsonPath("$.vehicleType").value("Bus"))
                .andExpect(jsonPath("$.registrationNumber").value(123456789L))
                .andExpect(jsonPath("$.insured").value(true));

        verify(vService, times(1)).getIndividualVehicleDetails(1);
    }

    // Get All Vehicle Details
    @Test
    void getAllVehicles() throws Exception {

        List<VehicleDTO> list = Arrays.asList(
                new VehicleDTO(1L, "Bus", 1111L, true),
                new VehicleDTO(2L, "Car", 2222L, false)
        );

        when(vService.getAllVehicleDetails()).thenReturn(list);

        mockMvc.perform(get("/vehicle/getallvehicle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        verify(vService, times(1)).getAllVehicleDetails();
    }

    // Create Vehicle Details
    @Test
    void createVehicle() throws Exception {

        VehicleDTO request = new VehicleDTO(1L, "Van", 5555L, true);

        when(vService.createVehicleDetails(any()))
                .thenReturn(request);

        mockMvc.perform(post("/vehicle/createvehicle")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.VehicleType").value("Van"));

        verify(vService, times(1)).createVehicleDetails(any());
    }

    // Update Vehicle Details
    @Test
    void updateVehicle() throws Exception {

        VehicleDTO updated = new VehicleDTO(1L, "MiniBus", 9999L, true);

        when(vService.updateVehicleDetails(eq(1), any()))
                .thenReturn(updated);

        mockMvc.perform(put("/vehicle/updatevehicle/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleType").value("MiniBus"));

        verify(vService, times(1)).updateVehicleDetails(eq(1), any());
    }

    //Delete Vehicle
    @Test
    void testDeleteVehicle() throws Exception {

        when(vService.deleteVehicleDetails(1))
                .thenReturn("Deleted Successfully");

        mockMvc.perform(delete("/vehicle/deletevehicle/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student Soft Deleted"));

        verify(vService, times(1)).deleteVehicleDetails(1);
    }
}