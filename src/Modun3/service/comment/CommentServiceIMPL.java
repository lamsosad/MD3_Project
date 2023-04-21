package Modun3.service.comment;

import Modun3.config.Config;
import Modun3.model.comment.Comment;

import java.util.List;

public class CommentServiceIMPL implements iCommentService {
    List<Comment> commentList = new Config<Comment>().readFromFile(Config.PATH_COMMENT);

    @Override
    public List<Comment> findAll() {
        return commentList;
    }

    @Override
    public void save(Comment comment) {
        if (findById(comment.getId()) == null) {
            commentList.add(comment);
        }
        new Config<Comment>().writeFromFile(Config.PATH_COMMENT, commentList);
    }

    @Override
    public Comment findById(int id) {
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getId() == id) {
                return commentList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
    }

}
