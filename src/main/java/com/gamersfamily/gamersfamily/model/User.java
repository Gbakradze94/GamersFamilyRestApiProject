package com.gamersfamily.gamersfamily.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User extends BaseEntity {

    @Builder
    private static User  of(long id, String username, String email, String password) {
        User user = new User();
        user.setId(id);
        user.username = username;
        user.email = email;
        user.password = password;
        return user;
    }

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "verificationCode", length = 64)
    private String verificationCode;

    @Column(name = "enabled", length = 64)
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;


    @OneToMany(mappedBy = "author")
    private List<Comment> comment;

    @OneToMany(mappedBy = "author")
    private List<SubComment> subCommentList;

    @OneToMany(mappedBy = "author")
    private List<Rating> ratings;


    @Override
    public String toString() {
        return "User{" +
                "ID: " + getId() +
                ", username = '" + getUsername() + '\'' +
                ", email = " + getEmail() +
                '}';
    }
}
