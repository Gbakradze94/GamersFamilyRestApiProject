package com.gamersfamily.gamersfamily.controller;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;
import com.gamersfamily.gamersfamily.service.SubCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subComments")
public class SubCommentController {
    private final SubCommentService subCommentService;

    public SubCommentController(SubCommentService subCommentService) {
        this.subCommentService = subCommentService;
    }


    @GetMapping("/getSubComments/{commentId}")
    @ApiOperation("returns subComments for specific comment")
    @ApiResponses(value = {@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "request is successful data is found"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "request is successful but not data is found ")})
    public ResponseEntity<List<SubCommentDtoOutput>> getSubCommentsForComment(@PathVariable long id) {
        List<SubCommentDtoOutput> outputs = subCommentService.getSubComments(id);
        if (!outputs.isEmpty()) return new ResponseEntity<>(outputs, HttpStatus.OK);
        else return new ResponseEntity<>(outputs, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/saveSubComment")
    @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "new subComment has been created")
    @ApiOperation("saves new comment and returns details about saved comment")
    public ResponseEntity<SubCommentDtoOutput> SaveSubCommentForComment(@RequestBody SubCommentDto dto) {
        return new ResponseEntity<>(subCommentService.saveSubComment(dto), HttpStatus.CREATED);

    }

    @PutMapping("/editSubComment")
    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "subComment has been edited")
    @ApiOperation("edits subComment and return edited subComment")
    public ResponseEntity<SubCommentDtoOutput> editSubComment(@RequestBody SubCommentDtoOutput dto) {
        return new ResponseEntity<>(subCommentService.editSubComment(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{subCommentId}/{authorId}")
    @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "subComment has been deleted")
    @ApiOperation("deletes subComment if subComment belongs to the author of the subComment")
    public ResponseEntity<SubCommentDtoOutput> deteleSubComment(@PathVariable long subCommentId, long authorId) {
        return new ResponseEntity<>(subCommentService.deleteSubComment(subCommentId, authorId), HttpStatus.NO_CONTENT);
    }
}
