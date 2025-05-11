package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommitRepository;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.repository.ProjectRepository;
import aiss.gitminer.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gitminer")
public class IssueController {
    @Autowired
    IssueService issueService;
    // GET http://localhost:8080/gitminer/issues
    @GetMapping("/issues")
    public List<Issue> getAllIssues(@RequestParam(name = "state", defaultValue = "all") String state,
                                    @RequestParam(name = "authorId", defaultValue = "all") String authorId,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String order,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size){
        return issueService.getAllIssues(state, authorId, title, order, page, size);
    }

    // GET http://localhost:8080/gitminer/issues/{issueId}
    @GetMapping("/issues/{issueId}")
    public Issue findOne(@PathVariable(value="issueId") String issueId) throws IssueNotFoundException{
        return issueService.findOne(issueId);
    }

}
