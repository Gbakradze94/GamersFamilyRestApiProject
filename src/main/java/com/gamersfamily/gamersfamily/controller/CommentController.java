package com.gamersfamily.gamersfamily.controller;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.dto.CommentDtoOutput;
import com.gamersfamily.gamersfamily.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getComments/{newsId}")
    @ApiOperation("returns comments for specific news")
    @ApiResponses(value = {@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "request is successful data is found"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "request is successful but not data is found ")})
    public ResponseEntity<List<CommentDtoOutput>> getCommentsForNews(@PathVariable long id) {
        List<CommentDtoOutput> outputs = commentService.getComments(id);
        System.out.println("result");
        log.info(String.valueOf(outputs));
        if (!outputs.isEmpty()) return new ResponseEntity<>(outputs, HttpStatus.OK);
        else return new ResponseEntity<>(outputs, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/saveComment")
    @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "new comment has been created")
    @ApiOperation("saves new comment and returns details about saved comment")
    public ResponseEntity<CommentDtoOutput> SaveCommentForNews(@RequestBody CommentDto dto) {
        return new ResponseEntity<>(commentService.saveComment(dto), HttpStatus.CREATED);

    }

    @PutMapping("/editComment")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "comment has been edited")
    @ApiOperation("edits comment and return edited comment")
    public ResponseEntity<CommentDtoOutput> editComment(@RequestBody CommentDtoOutput dto) {
        return new ResponseEntity<>(commentService.editComment(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}/{authorId}")
    @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "comment has been deleted")
    @ApiOperation("deletes comment if comment belongs to the author of the comment")
    public ResponseEntity<CommentDtoOutput> deteleComment(@PathVariable long commentId, long authorId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId, authorId), HttpStatus.NO_CONTENT);
    }



}
