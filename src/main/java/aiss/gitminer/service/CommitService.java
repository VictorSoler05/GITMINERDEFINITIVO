package aiss.gitminer.service;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CommitService {
    @Autowired
    CommitRepository commitRepository;

    public List<Commit> getAllCommits(){
        return commitRepository.findAll();    }

    public Commit findOne(String commitId) throws CommitNotFoundException {
        Optional<Commit> commit=commitRepository.findById(commitId);
        if(!commit.isPresent()) throw new CommitNotFoundException();
        return commit.get();
    }


}