package pl.adkr.hsbc.challenge.posting.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostEntityConverter {

    public PostEntity toPostEntity(String message, Long userId, LocalDateTime dateTime) {
        return new PostEntity(userId, message, dateTime);
    }
}
