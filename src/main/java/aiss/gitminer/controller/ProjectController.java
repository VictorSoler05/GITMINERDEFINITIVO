package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import aiss.gitminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    // GET http://localhost:8080/gitminer/projects
    @GetMapping
    public List<Project> findAll(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {

        return projectService.findAll(name, order, page, size);
    }

    // GET http://localhost:8080/gitminer/projects/{id}
    @GetMapping("/{id}")
    public Project findOne(@PathVariable String id) throws ProjectNotFoundException{
        return projectService.findOne(id);
    }

    // POST http://localhost:8080/gitminer/projects
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody @Valid Project project) {
        return projectService.createProject(project);
    }

    // PUT http://localhost:8080/gitminer/projects/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@RequestBody @Valid Project updatedProject, @PathVariable String id) throws ProjectNotFoundException{
        projectService.updateProject(updatedProject,id);

    }

    // DELETE http://localhost:8080/gitminer/projects/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }
}
