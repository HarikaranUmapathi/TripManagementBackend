package com.helloworld.tripmanagementsystem.Repository;

import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import static javax.swing.text.html.HTML.Tag.SELECT;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
    @Query("SELECT v.vehicleId from VehicleEntity v")
    List<Number> findAllVehicleId();

    @Query("SELECT v.vehicleType from VehicleEntity v")
    List<String> findAllVehicleType();

    VehicleEntity findByVehicleType(String vehicleType);
}
