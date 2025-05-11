package aiss.gitminer.controller;

import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.ProjectRepository;
import aiss.gitminer.service.CommitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="Commit", description = "Commit management API")
@RestController
@RequestMapping("/gitminer")
public class CommitController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommitService commitService;
    @Operation(
            summary = "Retrieve a Commits",
            description = "Get a list of commits",
            tags = { "Commit", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de commits encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Commit.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listado de commits no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/commits
    @GetMapping("/commits")
    public List<Commit> getAllCommits(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String order,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size)  {
        return commitService.getAllCommits(title, order, page, size);
    }

    @Operation(
            summary = "Retrieve a Commit by Id",
            description = "Get a commit by specific id",
            tags = { "Commit", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Commit encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Commit.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Commit no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/commits/{commitId}
    @GetMapping("/commits/{commitId}")
    public Commit findOne(@Parameter(description = "id of commit to be searched") @PathVariable(value="commitId") String commitId) throws CommitNotFoundException {
        return commitService.findOne(commitId);
    }

}

