package pl.adkr.hsbc.challenge.posting.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class SubmitPostRequest {

    @NotNull(message = "{post.validation.request.userId}")
    private Long userId;

    @NotBlank(message = "{post.validation.request.message}")
    @Size(min = 3, max = 140)
    private String message;

}
