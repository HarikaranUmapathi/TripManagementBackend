package com.helloworld.tripmanagementsystem.Controller;

import com.helloworld.tripmanagementsystem.DTO.DriverDTO;
import com.helloworld.tripmanagementsystem.Service.DriverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // <-- includes get/post/put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(DriverController.class)
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DriverService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    // Get Individual Driver Details
    @Test
    void getDetails() throws Exception {

        DriverDTO driverDTO = new DriverDTO(37L, "Hari", 4, 678959876L);

        when(service.getIndividualDriverDetails(3))
                .thenReturn(ResponseEntity.ok(driverDTO));

        mockMvc.perform(get("/driver/getdriver/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(37)) // FIXED
                .andExpect(jsonPath("$.driverName").value("Hari"))
                .andExpect(jsonPath("$.experience").value(4))
                .andExpect(jsonPath("$.contactNumber").value(678959876));

        verify(service, times(1)).getIndividualDriverDetails(3);
    }

    // Get All Driver Details
    @Test
    void getAllDrivers() throws Exception {

        DriverDTO d1 = new DriverDTO(1L, "Hari", 2, 111111111L);
        DriverDTO d2 = new DriverDTO(2L, "Ravi", 4, 222222222L);

        when(service.getAllDriverDetails()).thenReturn(List.of(d1, d2));

        mockMvc.perform(get("/driver/getalldriver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(service, times(1)).getAllDriverDetails();
    }

    // Create Driver Details
    @Test
    void testCreateDriver() throws Exception {

        DriverDTO request = new DriverDTO(1L, "Hari", 4, 678959876L);
        DriverDTO response = new DriverDTO(1L, "Hari", 4, 678959876L);

        when(service.createDriverDetails(any())).thenReturn(response);

        mockMvc.perform(post("/driver/createdriver")   // FIXED URL
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(service, times(1)).createDriverDetails(any());
    }

    // Update Driver Details
    @Test
    void testUpdateDriver() throws Exception {

        DriverDTO request = new DriverDTO(3L, "Updated", 5, 999999999L);
        DriverDTO response = new DriverDTO(3L, "Updated", 5, 999999999L);

        when(service.updateDriverDetails(eq(3), any())).thenReturn(response);

        mockMvc.perform(put("/driver/updatedriver/3")  // FIXED URL
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.driverName").value("Updated"));

        verify(service, times(1)).updateDriverDetails(eq(3), any());
    }

    //Delete Driver
    @Test
    void testDeleteDriver() throws Exception {

        int tripId = 1;

        when(service.deleteDriverDetails(tripId))
                .thenReturn("Deleted Successfully");

        mockMvc.perform(delete("/driver/deleteDriver/{tripId}", tripId))
                .andExpect(status().isOk());
    }


}