package com.gamersfamily.gamersfamily.repositoryTests;

import com.gamersfamily.gamersfamily.model.Role;
import com.gamersfamily.gamersfamily.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application-test.properties")
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void testGetAllRoles(){
        List<Role> roles = roleRepository.findAll();
        Assertions.assertThat(roles).hasSizeGreaterThan(0);

        for (Role role : roles) {
            System.out.println(role);
        }
    }

}
