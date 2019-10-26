package pl.adkr.hsbc.challenge.post.entity;

import org.springframework.stereotype.Component;

@Component
public class PostEntityConverter {

    public PostEntity toPostEntity(String message, Long userId) {
        return PostEntity.builder().message(message).userId(userId).build();
    }

}
