package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User entity);

    @InheritInverseConfiguration
    User toUser(UserDto dto);

    List<UserDto> listToDto(List<User> entityList);

    @InheritInverseConfiguration
    List<User> listToDomain(List<UserDto> entityList);
}
