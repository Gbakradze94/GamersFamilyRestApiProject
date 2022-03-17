package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.Rating;
import com.gamersfamily.gamersfamily.model.Role;
import com.gamersfamily.gamersfamily.model.SubComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty
    @Size(min=3, message = "Username Should be at least 3 chars")
    private String username;

    @Email
    @NotEmpty
    @Size(min=5, message = "Email Should be at least 5 chars")
    private String email;

    @NotEmpty
    @Size(min=3, message = "Password Should be at least 3 chars")
    private String password;

    private String verificationCode;

    private boolean isEnabled;

    private Set<Role> roles;

    private List<Comment> comment;

    private List<SubComment> subCommentList;

    private List<Rating> ratings;

}
