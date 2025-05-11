package aiss.gitminer.service;


import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommitService {
    @Autowired
    CommitRepository commitRepository;

    public List<Commit> getAllCommits(String title, String order, int page, int size){
        Pageable paging;
        if(order != null) {
            if (order.startsWith("-"))
                paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            else
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
        }
        else
            paging = PageRequest.of(page, size);

        Page<Commit> pageCommits;

        if (title ==null){
            pageCommits = commitRepository.findAll(paging);
        } else {
            pageCommits = commitRepository.findByTitle(title, paging);
        }

        return pageCommits.getContent();
    }

    public Commit findOne(String commitId) throws CommitNotFoundException {
        Optional<Commit> commit=commitRepository.findById(commitId);
        if(!commit.isPresent()) throw new CommitNotFoundException();
        return commit.get();
    }


}