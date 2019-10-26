package pl.adkr.hsbc.challenge.post.domain;

import java.util.Optional;

public interface PostStore {

    Optional<Post> storePost(String message, Long userId);
}
