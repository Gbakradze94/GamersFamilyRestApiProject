package com.gamersfamily.gamersfamily.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {



//    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$", message ="username should contain at least one digit, one upper case character, one lowercase character and  should be at least 6 characters long" )
    private String username;

    @Email
    @NotEmpty
    @Size(min = 5, message = "Email should be at least 5 char")
    private String email;


//    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message ="password should contain at least one digit, one upper case character, one lowercase character, one special symbol (@$!%*?&) and  should be at least 8 characters long")
    private String password;
}
