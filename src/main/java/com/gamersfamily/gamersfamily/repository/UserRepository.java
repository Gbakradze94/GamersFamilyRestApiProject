package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> findById(long Id);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

    @Query("UPDATE User u SET u.isEnabled = true, u.verificationCode = null WHERE u.id = ?1")
    @Modifying
    void enable(Long id);
}
