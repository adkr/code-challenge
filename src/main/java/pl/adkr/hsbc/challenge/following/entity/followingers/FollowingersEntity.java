package pl.adkr.hsbc.challenge.following.entity.followingers;

import lombok.*;
import pl.adkr.hsbc.challenge.following.entity.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class FollowingersEntity implements Serializable {

    //TODO same approach in terms of TAGS in posts - many to many... Skipping so far

    @EmbeddedId
    private FollowingersEntityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followerId")
    @NonNull
    private UserEntity follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("followingId")
    @NonNull
    private UserEntity following;

}
