package com.gamersfamily.gamersfamily.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotEmpty(message = "Username or Email should not be empty")
    @Size(min = 4, message = "Username or Email should be at least 4 char")
    private String email;
    @NotEmpty(message = "Password Should not be empty")
    @Size(min = 3, message = "Password should be at least 3 char")
    private String password;
}
