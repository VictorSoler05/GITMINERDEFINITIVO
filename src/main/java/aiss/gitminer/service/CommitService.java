package aiss.gitminer.service;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitService {
    @Autowired
    CommitRepository commitRepository;

    public List<Commit> getAllCommits(){
        return commitRepository.findAll();    }

    public Commit findOne(String commitId) throws CommitNotFoundException {
        return commitRepository.findById(commitId).get();
    }


}