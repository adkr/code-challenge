package pl.adkr.hsbc.challenge.user.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class UserEntity implements Serializable {

    @Value("${user.validation.len.min}")
    private static final int MSG_MIN_LEN = 3;

    @Value("${user.validation.len.max}")
    private static final int MSG_MAX_LEN = 140;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String login;

}
