package com.gamersfamily.gamersfamily.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {

    @NotEmpty
    @Size(min = 4, message = "Username should be at least 4 char")
    private String username;

    @Email
    @NotEmpty
    @Size(min = 5, message = "Email should be at least 5 char")
    private String email;

    @NotEmpty
    @Size(min = 3, message = "Password Should be at least 3 chars")
    private String password;
}
