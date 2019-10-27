package pl.adkr.hsbc.challenge.posting.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class GetPostsRequest {

    @NotNull
    private Long userId;
}
