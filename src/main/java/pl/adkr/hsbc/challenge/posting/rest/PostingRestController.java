package pl.adkr.hsbc.challenge.posting.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.adkr.hsbc.challenge.posting.domain.Post;
import pl.adkr.hsbc.challenge.posting.domain.PostService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/message")
@Slf4j
@Api(value = "Post management")
public class PostingRestController implements PostingRestValidationExceptionHandler {
    // TODO consider smaller, more specialized interfaces per posting and retrieving?

    private static final String POST_ID = "postId";

    private final PostService postService;

    @Autowired
    public PostingRestController(PostService postService) {
        this.postService = postService;
    }

    @PutMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Submit a post")
    public ResponseEntity<Post> submitPost(@RequestBody @Valid SubmitPostRequest req) {
        log.info("Calling submitPost {}", req);
        Optional<Post> savedMessage = postService.storePost(req.getMessage(), req.getUserId());
        return savedMessage
                .map(post -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(post))

                //FIXME handle unexpected case!
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of posts ordered in reverse chronological order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a list of posts"),
            @ApiResponse(code = 404, message = "Post not found")
    })
    public ResponseEntity<List<Post>> getPosts(@RequestBody @Valid GetPostsRequest req) {
        //FIXME add pagination
        List<Post> postsForUser = postService.getPostsForUser(req.getUserId());
        return ResponseEntity
                .status(postsForUser.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .body(postsForUser);
    }

    @GetMapping(path = "/{" + POST_ID + "}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a post by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved post"),
            @ApiResponse(code = 404, message = "Post not found")
    })
    public ResponseEntity<Post> getPost(@PathVariable(POST_ID) Long postId) {
        Optional<Post> post = postService.getPost(postId);
        return post
                .map(value -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(value))
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @Override
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }
}
