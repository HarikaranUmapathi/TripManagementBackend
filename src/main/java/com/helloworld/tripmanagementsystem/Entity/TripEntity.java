package com.helloworld.tripmanagementsystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)


public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    private String bookedBy;  // This get the value of UserId

    private String bookedDate;      //- 2026-02-20    - 2025-12-31

    private String duration;

    private String bookedOn;  //Online or Offline

    private String totalTripDistance;

    private String tripPlace;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_name",referencedColumnName = "driverName")
    private DriverEntity driver;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "vehicle_type",referencedColumnName = "vehicleType")
  private VehicleEntity vehicle;


    private Boolean SoftDelete = false;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDate updatedDate;

}

