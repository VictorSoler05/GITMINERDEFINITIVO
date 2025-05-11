package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.CommitRepository;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.repository.ProjectRepository;
import aiss.gitminer.service.IssueService;
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
import java.util.Optional;
import java.util.stream.Collectors;
@Tag(name = "Issue", description = "Issue management API")
@RestController
@RequestMapping("/gitminer")
public class IssueController {
    @Autowired
    IssueService issueService;
    @Operation(
            summary = "Retrieve an Issues",
            description = "Get a list of issues",
            tags = { "Issue", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de issues encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Issue.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listado de issues no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/issues
    @GetMapping("/issues")
    public List<Issue> getAllIssues(@RequestParam(name = "state", defaultValue = "all") String state,
                                    @RequestParam(name = "authorId", defaultValue = "all") String authorId,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String order,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size){
        return issueService.getAllIssues(state, authorId, title, order, page, size);
    }

    @Operation(
            summary = "Retrieve an Issue by Id",
            description = "Get an issue by specific id",
            tags = { "Issue", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Issue encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Issue.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Issue no encontrada",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/issues/{issueId}
    @GetMapping("/issues/{issueId}")
    public Issue findOne(@Parameter(description = "id of issue to be searched") @PathVariable(value="issueId") String issueId) throws IssueNotFoundException{
        return issueService.findOne(issueId);
    }

}

