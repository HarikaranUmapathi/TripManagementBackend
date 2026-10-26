package com.helloworld.tripmanagementsystem.Mapper;

import com.helloworld.tripmanagementsystem.DTO.VehicleDTO;
import com.helloworld.tripmanagementsystem.Entity.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface VehicleMapper {
    //VehicleMapper

    VehicleDTO toVehicleDTO(VehicleEntity entity);
    VehicleEntity toVehicleEntity(VehicleDTO dto);
}
