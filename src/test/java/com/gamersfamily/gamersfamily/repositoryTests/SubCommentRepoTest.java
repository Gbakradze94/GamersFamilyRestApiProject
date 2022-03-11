package com.gamersfamily.gamersfamily.repositoryTests;

import com.gamersfamily.gamersfamily.model.SubComment;
import com.gamersfamily.gamersfamily.repository.SubCommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class SubCommentRepoTest {
    @Autowired
    SubCommentRepository subCommentRepository;

    @Test(expected = IllegalArgumentException.class)
    public void DeletesubCommentEntityTest() {
        subCommentRepository.deleteById(400L);
        SubComment comment = subCommentRepository.findById(400L).
                orElseThrow(() -> {
                    throw new IllegalArgumentException();
                });
    }
}
