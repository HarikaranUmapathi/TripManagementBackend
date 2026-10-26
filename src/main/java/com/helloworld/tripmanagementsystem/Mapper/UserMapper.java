package com.helloworld.tripmanagementsystem.Mapper;

import com.helloworld.tripmanagementsystem.DTO.UserDTO;
import com.helloworld.tripmanagementsystem.Entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(UserEntity entity);
    UserEntity toUserEntity(UserDTO dto);
}