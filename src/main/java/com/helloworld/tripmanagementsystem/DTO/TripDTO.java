package com.helloworld.tripmanagementsystem.DTO;


import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private Integer tripId;

    private String bookedBy;  // This get the value of UserId

    private String bookedDate;      //- 2026-02-20    - 2025-12-31

    private String duration;

    private String bookedOn;  //Online or Offline

    private String totalTripDistance;

    private String vehicleType;

    private String driverName;



    private String tripPlace;

}
