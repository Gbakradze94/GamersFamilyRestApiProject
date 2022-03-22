package com.gamersfamily.gamersfamily.controllerTests;

public class Endpoint {

    private Endpoint() {
        throw new IllegalArgumentException("this class is not meant to be instantiated");
    }

    public static final String GET_COMMENTS = "/api/v1/comments/getComments/{newsId}";
    public static final String SAVE_COMMENT = "/api/v1/comments/saveComment";
    public static final String EDIT_COMMENT = "/api/v1/comments/editComment";
    public static final String DELETE_COMMENT = "/api/v1/comments/{commentId}/{authorId}";
    public static final String GET_SUB_COMMENTS = "/api/v1/subComments/getSubComments/{commentId}";
    public static final String SAVE_SUB_COMMENT = "/api/v1/subComments/saveSubComment";
    public static final String EDIT_SUB_COMMENT = "/api/v1/subComments/editSubComment";
    public static final String DELETE_SUB_COMMENT = "/api/v1/subComments/{subCommentId}/{authorId}";
}
