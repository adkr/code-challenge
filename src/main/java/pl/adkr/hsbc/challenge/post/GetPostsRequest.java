package pl.adkr.hsbc.challenge.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPostsRequest {

    @NotNull
    private Long userId;
}
