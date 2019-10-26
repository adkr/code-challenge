package pl.adkr.hsbc.challenge.post.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Post {

    private final String message;
    private final Long userId;
    private final LocalDateTime publicationDateTime;

}
