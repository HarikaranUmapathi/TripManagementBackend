package com.helloworld.tripmanagementsystem.Mapper;

import com.helloworld.tripmanagementsystem.DTO.DriverDTO;
import com.helloworld.tripmanagementsystem.DTO.TripDTO;
import com.helloworld.tripmanagementsystem.DTO.VehicleDTO;
import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import com.helloworld.tripmanagementsystem.Entity.TripEntity;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")

public interface TripMapper {

    @Mapping(source = "driver.driverName", target = "driverName")
    @Mapping(source = "vehicle.vehicleType", target = "vehicleType")
    TripDTO toTripDTO(TripEntity entity);

    @Mapping(source = "driverName", target = "driver.driverName")
    @Mapping(source = "vehicleType", target = "vehicle.vehicleType")
    TripEntity toTripEntity(TripDTO dto);
}
