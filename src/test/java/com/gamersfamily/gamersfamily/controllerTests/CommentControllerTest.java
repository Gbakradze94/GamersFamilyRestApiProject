package com.gamersfamily.gamersfamily.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamersfamily.gamersfamily.GamersFamilyRestApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(classes = {GamersFamilyRestApiApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CommentControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void getCommentsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(Endpoint.GET_COMMENTS, 200)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("").value("val"));

    }

    @Test
    public void saveCommentTest() {

    }

    @Test
    public void editCommentTest() {

    }

    @Test
    public void deleteCommentTest() {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
