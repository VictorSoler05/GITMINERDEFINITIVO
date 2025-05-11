package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.service.CommentService;
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

@Tag(name = "Comment", description = "Comment management API")
@RestController
@RequestMapping("/gitminer")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Operation(
            summary = "Retrieve a Comments",
            description = "Get a list of comments",
            tags = { "Comment", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de comentarios encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listado de comentarios no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/comments
    @GetMapping("/comments")
    public List<Comment> getAllComments(@RequestParam(required = false) String order,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){
        return commentService.getAllComments(order, page, size);
    }
    @Operation(
            summary = "Retrieve a Comment by Id",
            description = "Get a comment by specific id",
            tags = { "Comment", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comentario encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comentario no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/comments/{commentId}
    @GetMapping("/comments/{commentId}")
    public Comment findOne(@Parameter(description = "id of comment to be searched") @PathVariable(value="commentId") String commentId) throws CommentNotFoundException {
        return commentService.findOne(commentId);
    }

    @Operation(
            summary = "Retrieve a Comments by Issue Id",
            description = "Get a list of comments by specific issue id",
            tags = { "Comment", "get" }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de comentarios encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Listado de comentarios no encontrado",
                    content = @Content(schema = @Schema())
            )
    })
    // GET http://localhost:8080/gitminer/issues/{issueId}/comments
    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getAllCommentsByIssue(@Parameter(description = "id of issue to be searched")@PathVariable(value="issueId") String issueId) throws IssueNotFoundException {
        return commentService.getAllCommentsByIssue(issueId);
    }
}
