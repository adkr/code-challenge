package pl.adkr.hsbc.challenge.post.domain;

import java.util.List;
import java.util.Optional;

public interface PostProvider {

    Optional<Post> getPost(Long postId);

    List<Post> getPostsForUser(Long userId);
}
