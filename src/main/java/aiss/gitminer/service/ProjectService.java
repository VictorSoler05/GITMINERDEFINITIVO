package aiss.gitminer.service;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
        @Autowired
        ProjectRepository projectRepository;

        public List<Project> getAllProjects(){
            return projectRepository.findAll();
        }

        public Project findOne(String projectId) throws ProjectNotFoundException {
            return projectRepository.findById(projectId).get();
        }
}
