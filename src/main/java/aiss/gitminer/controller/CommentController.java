package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer")
public class CommentController {

    @Autowired
    CommentService commentService;

    // GET http://localhost:8080/gitminer/comments
    @GetMapping("/comments")
    public List<Comment> getAllComments(@RequestParam(required = false) String order,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){
        return commentService.getAllComments(order, page, size);
    }

    // GET http://localhost:8080/gitminer/comments/{commentId}
    @GetMapping("/comments/{commentId}")
    public Comment findOne(@PathVariable(value="commentId") String commentId) throws CommentNotFoundException {
        return commentService.findOne(commentId);
    }

    // GET http://localhost:8080/gitminer/issues/{issueId}/comments
    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getAllCommentsByIssue(@PathVariable(value="issueId") String issueId) throws IssueNotFoundException {
        return commentService.getAllCommentsByIssue(issueId);
    }
}
