package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer")
public class CommentController {
    @Autowired
    IssueRepository issueRepository;

    @Autowired
    CommentService commentService;
    // GET http://localhost:8080/gitminer/issues/{issueId}
    @GetMapping("/comments")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    // GET http://localhost:8080/gitminer/comments/{commentId}
    @GetMapping("/comments/{commentId}")
    public Comment findOne(@PathVariable(value="commentId") String commentId) throws CommentNotFoundException {
        return commentService.findOne(commentId);
    }
}
