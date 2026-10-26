package com.helloworld.tripmanagementsystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {


    private Long vehicleId;

    private String vehicleType;

    private long registrationNumber;

    private Boolean insured;
}
