package pl.adkr.hsbc.challenge.wall.domain;

import lombok.Value;
import pl.adkr.hsbc.challenge.post.domain.Post;

import java.util.List;

@Value
public class Wall {

    List<Post> posts;
}
