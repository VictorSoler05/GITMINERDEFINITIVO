package aiss.gitminer.service;


import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment findOne(String commentId) throws CommentNotFoundException {
        Integer a=2;
        return commentRepository.findById(commentId).get();
    }


}
