package com.gamersfamily.gamersfamily.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private static User  of(long id, String username, String email, String password, boolean enabled, String verificationcode) {
        User user = new User();
        user.setId(id);
        user.username = username;
        user.email = email;
        user.password = password;
        user.enabled = enabled;
        user.verificationcode = verificationcode;
        return user;
    }

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "verificationCode")
    private String verificationcode;

    @Column(name = "enabled")
    public boolean enabled;

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    public boolean isEnabled(){
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID: " + getId() +
                ", username = '" + getUsername() + '\'' +
                ", email = " + getEmail() +
                '}';
    }
}
