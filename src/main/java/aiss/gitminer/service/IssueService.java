package aiss.gitminer.service;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommitRepository;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {
    @Autowired
    IssueRepository issueRepository;

    public List<Issue> getAllIssues(String state, String authorId){
        return issueRepository.findAll().stream()
                .filter(issue -> state.equals("all") || issue.getState().equalsIgnoreCase(state))
                .filter(issue -> authorId.equals("all") || issue.getAuthor().getId().equalsIgnoreCase(authorId))
                .collect(Collectors.toUnmodifiableList());
    }

    public Issue findOne(String issueId) throws IssueNotFoundException {
        return issueRepository.findById(issueId).get();
    }


}