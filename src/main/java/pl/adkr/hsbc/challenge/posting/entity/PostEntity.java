package pl.adkr.hsbc.challenge.posting.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PostEntity implements Serializable {

    @Value("${message.validation.len.min}")
    private static final int MSG_MIN_LEN = 3;

    @Value("${message.validation.len.max}")
    private static final int MSG_MAX_LEN = 140;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NonNull
    private Long userId;

    @Size(min = MSG_MIN_LEN, max = MSG_MAX_LEN, message = "Message content must be between " + MSG_MIN_LEN + " and " + MSG_MAX_LEN + " characters.")
    @NonNull
    private String message;

    @NotNull
    private LocalDateTime createDateTime = LocalDateTime.now();

    public PostEntity(Long userId, String message, LocalDateTime createDateTime) {
        this.userId = userId;
        this.message = message;
        this.createDateTime = createDateTime;
    }
}
