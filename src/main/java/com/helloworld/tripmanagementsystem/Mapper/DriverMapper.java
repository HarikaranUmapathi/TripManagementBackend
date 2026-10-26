package com.helloworld.tripmanagementsystem.Mapper;

import com.helloworld.tripmanagementsystem.DTO.DriverDTO;
import com.helloworld.tripmanagementsystem.Entity.DriverEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface DriverMapper {

// DriverMapper
    DriverDTO toDriverDTO(DriverEntity entity);
    DriverEntity toDriverEntity(DriverDTO dto);
}
