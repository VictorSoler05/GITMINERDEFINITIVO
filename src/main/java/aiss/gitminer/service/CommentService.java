package aiss.gitminer.service;


import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IssueRepository issueRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment findOne(String commentId) throws CommentNotFoundException {
        return commentRepository.findById(commentId).get();
    }


    public List<Comment> getAllCommentsByIssue(String issueId) {
        return issueRepository.findById(issueId).get().getComments();
    }
}
