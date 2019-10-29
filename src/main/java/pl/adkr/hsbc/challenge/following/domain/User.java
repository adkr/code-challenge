package pl.adkr.hsbc.challenge.following.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    private Long id;
    private String login;

}
