package aiss.gitminer.service;


import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        Optional<Comment> comment=commentRepository.findById(commentId);
        if(!comment.isPresent()) throw new CommentNotFoundException();
        return comment.get();
    }


    public List<Comment> getAllCommentsByIssue(String issueId) throws IssueNotFoundException {
            Optional<Issue> issue=issueRepository.findById(issueId);
            if(!issue.isPresent()) throw new IssueNotFoundException();
            return issue.get().getComments();
    }
}
