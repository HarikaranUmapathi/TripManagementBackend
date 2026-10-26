package com.helloworld.tripmanagementsystem.Controller;

import com.helloworld.tripmanagementsystem.DTO.TripDTO;

import com.helloworld.tripmanagementsystem.Service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TripService service;

    @Autowired
    ObjectMapper Mapper;

    //Get individual Trip Details
    @Test
    void testGetTripById() throws Exception {

        TripDTO response = new TripDTO();
        response.setTripId(1);
        response.setBookedBy("Hari");

        when(service.getIndividualTripDetails(1))
                .thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(get("/trip/gettrip/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.TripId").value(1))
                .andExpect(jsonPath("$.BookedBy").value("Hari"));

        verify(service,times(1)).getIndividualTripDetails(1);
    }

    //Get All Trip Details
    @Test
    void testGetAllTrips() throws Exception {

        TripDTO trip = new TripDTO();
        trip.setTripId(1);
        trip.setBookedBy("Hari");

        when(service.getAllTripDetails())
                .thenReturn(List.of(trip));

        mockMvc.perform(get("/trip/getalltrip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].TripId").value(1));

        verify(service, times(1)).getAllTripDetails();
    }

    //Create Trip Details
    @Test
    void testCreateTrip() throws Exception {

        TripDTO request = new TripDTO();
        request.setBookedBy("Hari");

        TripDTO response = new TripDTO();
        response.setTripId(1);
        response.setBookedBy("Hari");

        when(service.createTripDetails(any(TripDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/trip/createtrip")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.TripId").value(1));

        verify(service, times(1)).createTripDetails(any(TripDTO.class));
    }

    //Update Trip Details

    @Test
    void testUpdateTrip() throws Exception {

        TripDTO request = new TripDTO();
        request.setBookedBy("Hari");

        TripDTO response = new TripDTO();
        response.setTripId(1);
        response.setBookedBy("Hari");

        when(service.updateTripDetails(eq(1), any(TripDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/trip/updatetrip/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.BookedBy").value("Hari"));

        verify(service, times(1))
                .updateTripDetails(eq(1), any(TripDTO.class));
    }

    //Soft delete the Trip Details
    @Test
    void testSoftDeleteTrip() throws Exception {

        when(service.softDeleteTripDetails(1))
                .thenReturn("Successfully Soft Deleted");

        mockMvc.perform(delete("/trip/deletetrip/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully Soft Deleted"));

        verify(service, times(1)).softDeleteTripDetails(1);
    }
}
