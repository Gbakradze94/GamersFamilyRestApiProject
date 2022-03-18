package com.gamersfamily.gamersfamily.serviceTests;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;
import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import com.gamersfamily.gamersfamily.service.SubCommentService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application-test.properties")
public class SubCommentServiceTest {
    @Autowired
    SubCommentService subCommentService;

    @Test
    @WithMockUser(username = "email@gmail.ru")
    public void b_saveSubCommentTest() {
        SubCommentDto dto = SubCommentDto.builder().body("i do not agree with your opinion")
                .commentId(300)
                .userId(100)
                .createdAt(LocalDateTime.now())
                .build();
        SubCommentDtoOutput output = subCommentService.saveSubComment(dto);
        Assert.assertEquals("i do not agree with your opinion", output.getBody());
        Assert.assertEquals("anna", output.getUsername());

    }

    @Test
    @WithMockUser(username = "email@gmail.ru")
    public void a_getSubCommentsTest() {
        List<SubCommentDtoOutput> outputList = subCommentService.getSubComments(300);
        SubCommentDtoOutput output = outputList.get(0);
        Assert.assertEquals(400, output.getId());
        Assert.assertEquals("my subcomment to this comment", output.getBody());
        Assert.assertEquals("anna", output.getUsername());

    }

    @Test
    @WithMockUser(username = "email@gmail.ru")
    public void c_editSubCommentTest() {
        SubCommentDtoOutput subComment = new SubCommentDtoOutput("anna", 400L, LocalDateTime.now());
        subComment.setCreatedAt(LocalDateTime.parse("2022-03-06T19:00:22.032804", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        subComment.setBody("i want to modify my subComment");
        subComment.setCommentId(300);
        subComment.setUserId(100);

        SubCommentDtoOutput output = subCommentService.editSubComment(subComment);
        System.out.println(output);
        assertEquals("i want to modify my subComment", output.getBody());
        assertEquals("anna", output.getUsername());
        assertEquals(400, output.getId());

    }

    @Test
    @WithMockUser(username = "email@gmail.ru")
    public void dab_deleteSubCommentTest() {
        SubCommentDtoOutput output = subCommentService.deleteSubComment(400, 100);
        assertEquals(400, output.getId());
    }

    @Test(expected = BlogAPIException.class)
    @WithMockUser(username = "mishamisha@gmail.ru")
    public void d_deletesubCommentTestWhenUserIsIllegal() {
        subCommentService.deleteSubComment(400, 101);

    }

    @Test(expected = BlogAPIException.class)
    @WithMockUser(username = "email@gmail.ru")
    public void da_deletesubCommentTestWhenCommentIdDoesNotExist() {
        subCommentService.deleteSubComment(200, 100);
    }

}
