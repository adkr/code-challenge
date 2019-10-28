package pl.adkr.hsbc.challenge.posting.domain;

import java.util.Optional;

public interface PostStore {

    Optional<Post> storePost(String message, Long userId);
}
