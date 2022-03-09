package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.RoleDto;
import com.gamersfamily.gamersfamily.model.Role;

import java.util.List;

public interface RoleService {
    RoleDto getRoleById(long roleId);
    List<RoleDto> getAllRoles();
    Role checkRoleById(long roleId);
}
