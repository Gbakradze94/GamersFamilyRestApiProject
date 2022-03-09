package com.gamersfamily.gamersfamily.service.ServiceImpl;

import com.gamersfamily.gamersfamily.dto.RoleDto;
import com.gamersfamily.gamersfamily.exception.ResourceNotFoundException;
import com.gamersfamily.gamersfamily.mapper.RoleMapper;
import com.gamersfamily.gamersfamily.model.Role;
import com.gamersfamily.gamersfamily.repository.RoleRepository;
import com.gamersfamily.gamersfamily.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto getRoleById(long roleId) {
        Role role = checkRoleById(roleId);
        return roleMapper.entityToDto(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Role checkRoleById(long roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                ()-> new ResourceNotFoundException("Role", "id", roleId));
    }
}
