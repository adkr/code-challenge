package pl.adkr.hsbc.challenge.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adkr.hsbc.challenge.post.domain.Post;
import pl.adkr.hsbc.challenge.post.domain.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/message")
public class PostRestController {

    private static final String USER_ID = "userId";
    private static final String POST_ID = "postId";

    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PutMapping(path = "/{" + USER_ID + "}")
    @ResponseBody
    public ResponseEntity<Post> submitPost(@RequestBody String message, @PathVariable(USER_ID) Long userId) {
        Optional<Post> savedMessage = postService.storePost(message, userId);
        return savedMessage
                .map(post -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(post))

                //FIXME handle unexpected case!
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Post>> getPosts(@RequestBody Long userId) {
        List<Post> postsForUser = postService.getPostsForUser(userId);
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

}
