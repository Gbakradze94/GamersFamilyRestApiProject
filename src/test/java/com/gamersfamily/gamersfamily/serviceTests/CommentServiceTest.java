package com.gamersfamily.gamersfamily.serviceTests;


import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.dto.CommentDtoOutput;
import com.gamersfamily.gamersfamily.service.CommentService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentServiceTest {


    @Autowired
    private CommentService commentService;


    @Test
    public void ab_testSaveComment() {
        LocalDateTime localDateTime = LocalDateTime.now();
        CommentDto dto = CommentDto
                .builder()
                .body("my comment first ").userId(100).newsId(200).createdAt(localDateTime).build();
        CommentDto commentDtoOutput = commentService.saveComment(dto);
        assertEquals(dto.getNewsId(), commentDtoOutput.getNewsId());
        assertEquals(dto.getBody(), commentDtoOutput.getBody());
        assertEquals(dto.getCreatedAt(), commentDtoOutput.getCreatedAt());

    }

    @Test
    public void a_getCommentsTest() {
        List<CommentDtoOutput> commentDtoList = commentService.getComments(200);
        CommentDtoOutput commentDto = commentDtoList.get(0);
        System.out.println(commentDto);
        assertEquals("anna", commentDto.getUsername());
        assertEquals("first comment to this news", commentDto.getBody());
        assertEquals(Long.valueOf(300).toString(), commentDto.getId().toString());

    }

    @Test
    public void abc_editCommentTest() {
        CommentDtoOutput commentDtoOutput =new CommentDtoOutput("anna",300l, LocalDateTime.now());
        commentDtoOutput.setCreatedAt(LocalDateTime.parse("2022-02-06T20:00:59.032804", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        commentDtoOutput.setBody("comment is edited");
        commentDtoOutput.setUserId(100);
        commentDtoOutput.setNewsId(200);

        CommentDtoOutput output = commentService.editComment(commentDtoOutput);
        System.out.println(output);
        assertEquals("comment is edited", output.getBody());
        assertEquals("anna", output.getUsername());
        assertEquals(Long.valueOf(300).toString(), output.getId().toString());


    }
    @Test(expected =IllegalArgumentException.class)
    public void abcd_editCommentWhenCommentDoesNotBelongToUser(){
        CommentDtoOutput commentDtoOutput=new CommentDtoOutput("anna",300l,LocalDateTime.now());
        commentDtoOutput.setCreatedAt(LocalDateTime.parse("2022-02-06T20:00:59.032804",DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        commentDtoOutput.setBody("comment is edited");
        commentDtoOutput.setUserId(150);
        commentDtoOutput.setNewsId(200);

        CommentDtoOutput output=commentService.editComment(commentDtoOutput);

        }

    @Test
    public void abcde_deleteCommentTest(){
       CommentDtoOutput output=commentService.deleteComment(300,100);
        assertEquals(Long.valueOf(300L).toString(),output.getId().toString());
    }
    @Test(expected=IllegalArgumentException.class)
    public void b_deleteCommentTestWhenUserIsIllegal(){
        CommentDtoOutput output=commentService.deleteComment(300,102);

    }
    @Test(expected=IllegalArgumentException.class)
    public void ba_deleteCommentTestWhenCommentIdDoesNotExist(){
        CommentDtoOutput output=commentService.deleteComment(200,100);
    }
}
