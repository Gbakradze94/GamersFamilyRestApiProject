package com.gamersfamily.gamersfamily.repositoryTests;

import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class NewsRepositoryTest {

    @Autowired
   private  NewsRepository newsRepository;

   @Test
    public void findByIdTest(){
        News news= newsRepository.findById(200).orElseThrow(()-> {throw new IllegalArgumentException();});
        assertEquals(200,news.getId());
        assertEquals("this is news",news.getBody().toString());


    }
}
