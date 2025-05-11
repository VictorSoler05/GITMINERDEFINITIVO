package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import aiss.gitminer.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
//http://localhost:8080/swagger-ui/index.html
@Tag(name = "Project", description = "Project management API")
@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Operation(
            summary = "Retrieve a Projects",
            description = "Get a list of projects",
            tags = { "Project", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de proyectos encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listado de proyectos no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/projects
    @GetMapping
    public List<Project> findAll(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String order,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {

        return projectService.findAll(name, order, page, size);
    }

    @Operation(
            summary = "Retrieve a Project by Id",
            description = "Get a Project by id",
            tags = { "Project", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Proyecto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/projects/{id}
    @GetMapping("/{id}")
    public Project findOne(@Parameter(description = "id of projects to be searched") @PathVariable String id) throws ProjectNotFoundException{
        return projectService.findOne(id);
    }

    @Operation(
            summary = "Create a Project",
            description = "Create a project",
            tags = { "Project", "post" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Proyecto creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // POST http://localhost:8080/gitminer/projects
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody @Valid Project project) {
        return projectService.createProject(project);
    }

    @Operation(
            summary = "Update a Project",
            description = "Update a project by specific id",
            tags = { "Project", "put" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Proyecto actualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error de formato en el cuerpo de la petición",
                    content = @Content(schema = @Schema())
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // PUT http://localhost:8080/gitminer/projects/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@RequestBody @Valid Project updatedProject,@Parameter(description = "id of project to be updated") @PathVariable String id) throws ProjectNotFoundException{
        projectService.updateProject(updatedProject,id);

    }

    @Operation(
            summary = "Delete a Project",
            description = "Delete a project by specific id",
            tags = { "Project", "delete" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Proyecto eliminado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Project.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proyecto no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // DELETE http://localhost:8080/gitminer/projects/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@Parameter(description = "id of project to be deleted")@PathVariable String id) {
        projectService.deleteProject(id);
    }
}
