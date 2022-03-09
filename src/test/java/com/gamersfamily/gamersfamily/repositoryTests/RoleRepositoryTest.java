package com.gamersfamily.gamersfamily.repositoryTests;

import com.gamersfamily.gamersfamily.dto.RoleDto;
import com.gamersfamily.gamersfamily.service.RoleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RoleRepositoryTest {

    private final RoleService roleService;

    RoleRepositoryTest(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    void testGetAllRoles(){
        List<RoleDto> roles = roleService.getAllRoles();
        Assertions.assertThat(roles).hasSizeGreaterThan(0);

        for (RoleDto role : roles) {
            System.out.println(role);
        }
    }

}
