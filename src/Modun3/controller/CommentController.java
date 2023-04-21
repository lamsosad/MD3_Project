package Modun3.controller;

import Modun3.model.comment.Comment;
import Modun3.service.comment.CommentServiceIMPL;
import Modun3.service.comment.iCommentService;


import java.util.List;

public class CommentController {
    iCommentService commentService = new CommentServiceIMPL();

    public void addCommentProduct(Comment comment) {
        commentService.save(comment);
    }
}
