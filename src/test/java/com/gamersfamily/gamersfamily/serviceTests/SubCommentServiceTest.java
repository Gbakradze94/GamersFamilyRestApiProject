package com.gamersfamily.gamersfamily.serviceTests;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;
import com.gamersfamily.gamersfamily.service.SubCommentService;
import org.junit.Assert;
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
public class SubCommentServiceTest {
    @Autowired
    SubCommentService subCommentService;

    @Test
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
    public void a_getSubCommentsTest() {
        List<SubCommentDtoOutput> outputList = subCommentService.getSubComments(300);
        SubCommentDtoOutput output = outputList.get(0);
        Assert.assertEquals(Long.valueOf(400).toString(), output.getId().toString());
        Assert.assertEquals("my subcomment to this comment", output.getBody());
        Assert.assertEquals("anna", output.getUsername());

    }

    @Test
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
        assertEquals(Long.valueOf(400).toString(), output.getId().toString());

    }

    @Test
    public void dab_deleteSubCommentTest() {
        SubCommentDtoOutput output = subCommentService.deleteSubComment(400, 100);
        assertEquals(Long.valueOf(400L).toString(), output.getId().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void d_deletesubCommentTestWhenUserIsIllegal() {
        subCommentService.deleteSubComment(400, 102);

    }

    @Test(expected = IllegalArgumentException.class)
    public void da_deletesubCommentTestWhenCommentIdDoesNotExist() {
        subCommentService.deleteSubComment(200, 100);
    }

}
