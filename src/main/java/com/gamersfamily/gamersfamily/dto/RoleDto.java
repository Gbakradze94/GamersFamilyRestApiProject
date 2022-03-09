package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    @NotEmpty
    @Size(min = 2, message = "Role Should Be at Least 2 chars")
    private String name;
}
