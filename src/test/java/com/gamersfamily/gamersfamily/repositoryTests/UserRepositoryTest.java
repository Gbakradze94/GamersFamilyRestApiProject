package com.gamersfamily.gamersfamily.repositoryTests;

import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByIdTest() {
        User user = repository.findById(100).orElseThrow(() -> {
            throw new IllegalArgumentException();
        });
        Assertions.assertEquals(100, user.getId());
        Assertions.assertEquals("email@gmail.ru", user.getEmail());
    }


}
