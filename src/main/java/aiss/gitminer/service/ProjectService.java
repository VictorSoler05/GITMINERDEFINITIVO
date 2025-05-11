package aiss.gitminer.service;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
        @Autowired
        ProjectRepository projectRepository;

        public List<Project> findAll(
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String order,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size){

            Pageable paging;
            if(order != null) {
                if (order.startsWith("-"))
                    paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
                else
                    paging = PageRequest.of(page, size, Sort.by(order).ascending());
            }
            else
                paging = PageRequest.of(page, size);

            Page<Project> pageProjects;

            if (name ==null){
                pageProjects = projectRepository.findAll(paging);
            } else {
                pageProjects = projectRepository.findByName(name, paging);
            }

            return pageProjects.getContent();
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
