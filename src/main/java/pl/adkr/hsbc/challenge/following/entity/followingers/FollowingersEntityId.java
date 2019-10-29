package pl.adkr.hsbc.challenge.following.entity.followingers;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class FollowingersEntityId implements Serializable {

    private Long followerId;

    private Long followingId;

}
