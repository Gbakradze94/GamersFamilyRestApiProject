package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.RoleDto;
import com.gamersfamily.gamersfamily.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleDto entityToDto(Role role){
        return modelMapper.map(role, RoleDto.class);
    }

    public Role dtoToEntity(RoleDto roleDto){
        return modelMapper.map(roleDto, Role.class);
    }
}
