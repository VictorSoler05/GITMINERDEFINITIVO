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
    public List<Commit> getAllCommits(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String order,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size)  {
        return commitService.getAllCommits(title, order, page, size);
    }

    // GET http://localhost:8080/gitminer/commits/{commitId}
    @GetMapping("/commits/{commitId}")
    public Commit findOne(@PathVariable(value="commitId") String commitId) throws CommitNotFoundException {
        return commitService.findOne(commitId);
    }

}
