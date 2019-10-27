package pl.adkr.hsbc.challenge.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.adkr.hsbc.challenge.post.domain.Post;
import pl.adkr.hsbc.challenge.post.domain.PostService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/message")
@Slf4j
public class PostRestController {

    private static final String USER_ID = "userId";
    private static final String POST_ID = "postId";

    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PutMapping(path = "/")
    @ResponseBody
    public ResponseEntity<Post> submitPost(@RequestBody @Valid SubmitPostRequest req) {
        Optional<Post> savedMessage = postService.storePost(req.getMessage(), req.getUserId());
        return savedMessage
                .map(post -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(post))

                //FIXME handle unexpected case!
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPosts(@RequestBody @Valid GetPostsRequest req) {
        List<Post> postsForUser = postService.getPostsForUser(req.getUserId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postsForUser);
    }

    @GetMapping(path = "/{" + POST_ID + "}")
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
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
