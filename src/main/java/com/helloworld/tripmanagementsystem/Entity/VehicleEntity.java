package com.helloworld.tripmanagementsystem.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;


    private String vehicleType;
    @Column(unique = true)
    private long registrationNumber;

    private Boolean insured;

}
