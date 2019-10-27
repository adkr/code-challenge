package pl.adkr.hsbc.challenge.posting.entity.post;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
public class PostEntity implements Serializable {

    @Value("${message.validation.len.min}")
    private static final int MSG_MIN_LEN = 3;

    @Value("${message.validation.len.max}")
    private static final int MSG_MAX_LEN = 140;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long userId;

    @Size(min = MSG_MIN_LEN, max = MSG_MAX_LEN, message = "Message content must be between " + MSG_MIN_LEN + " and " + MSG_MAX_LEN + " characters.")
    private String message;

    @NotNull
    private LocalDateTime createDateTime;

}
