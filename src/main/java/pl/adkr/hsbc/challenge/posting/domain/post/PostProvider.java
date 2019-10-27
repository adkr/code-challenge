package pl.adkr.hsbc.challenge.posting.domain.post;

import java.util.List;
import java.util.Optional;

public interface PostProvider {

    Optional<Post> getPost(Long postId);

    List<Post> getPostsForUser(Long userId);
}
