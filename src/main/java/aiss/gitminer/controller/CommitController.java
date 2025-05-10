package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommitRepository;
import aiss.gitminer.repository.ProjectRepository;
import aiss.gitminer.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer")
public class CommitController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommitService commitService;
    // GET http://localhost:8080/gitminer/commits
    @GetMapping("/commits")
    public List<Commit> getAllCommits()  {
        return commitService.getAllCommits();
    }

    // GET http://localhost:8080/gitminer/commits/{id}
    @GetMapping("/commits/{commitId}")
    public Commit findOne(@PathVariable(value="commitId") String commitId) throws CommentNotFoundException {
        try{
            return commitService.findOne(commitId);
        } catch (CommitNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
