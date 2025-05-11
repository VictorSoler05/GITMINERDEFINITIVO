package aiss.gitminer.service;


import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.CommentRepository;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IssueRepository issueRepository;

    public List<Comment> getAllComments(String order, int page, int size){
        Pageable paging;
        if (order != null) {
            Sort sort;

            if (order.startsWith("-")) {
                String sortBy = order.substring(1);
                sort = Sort.by(Sort.Direction.DESC, sortBy);
            } else {
                sort = Sort.by(Sort.Direction.ASC, order);
            }

            paging = PageRequest.of(page, size, sort);
        } else {
            paging = PageRequest.of(page, size);
        }

        Page<Comment> pageComments;
        pageComments = commentRepository.findAll(paging);

        return pageComments.getContent();
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
