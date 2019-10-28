package pl.adkr.hsbc.challenge.posting.domain;

import org.springframework.stereotype.Component;
import pl.adkr.hsbc.challenge.posting.entity.PostEntity;

import java.util.Optional;

@Component
public class PostConverter {

    Optional<Post> toPost(PostEntity postEntity) {
        return Optional
                .ofNullable(postEntity)
                .map(p -> toPost(p.getMessage(), p.getUserId(), p.getId()));
    }

    private Post toPost(String message, Long userId, Long postId) {
        return Post
                .builder()
                .id(postId)
                .message(message)
                .userId(userId)
                .build();
    }
}
