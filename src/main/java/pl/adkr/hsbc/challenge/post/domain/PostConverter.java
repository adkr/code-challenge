package pl.adkr.hsbc.challenge.post.domain;

import org.springframework.stereotype.Component;
import pl.adkr.hsbc.challenge.post.entity.PostEntity;

import java.util.Optional;

@Component
public class PostConverter {

    Optional<Post> toPost(PostEntity postEntity) {
        return Optional
                .ofNullable(postEntity)
                .map(p -> toPost(p.getMessage(), p.getUserId()));
    }

    private Post toPost(String message, Long userId) {
        return Post
                .builder()
                .message(message)
                .userId(userId)
                .build();
    }
}
