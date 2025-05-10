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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
        @Autowired
        ProjectRepository projectRepository;

        public List<Project> findAll(){
            return projectRepository.findAll();
        }

        public Project findOne(String projectId) throws ProjectNotFoundException {
            Optional<Project> project = projectRepository.findById(projectId);
            if (!project.isPresent()){
                throw new ProjectNotFoundException();
            }
            return project.get();
        }

    public Project createProject(Project project) {
        Project _project = projectRepository.save(project);
        return _project;
    }

    public void updateProject(Project updatedProject , String id) throws ProjectNotFoundException{
        Optional<Project> projectData = projectRepository.findById(id);
        if (!projectData.isPresent()){
            throw new ProjectNotFoundException();
        }
        Project _project = projectData.get();
        _project.setName(updatedProject.getName()) ;
        _project.setIssues(updatedProject.getIssues());
        _project.setIssues(updatedProject.getIssues()); ;
        _project.setWebUrl(updatedProject.getWebUrl());
        projectRepository.save(_project);
    }

    public void deleteProject(String id) {
        if (projectRepository.existsById(id)){
            projectRepository.deleteById(id);
        }
    }
}
