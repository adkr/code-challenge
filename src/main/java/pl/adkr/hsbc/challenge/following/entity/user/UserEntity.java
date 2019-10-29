package pl.adkr.hsbc.challenge.following.entity.user;

import lombok.*;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingersEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NonNull
    @Column(unique = true)
    private String login;

    @OneToMany(
            mappedBy = "follower",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FollowingersEntity> following = new ArrayList<>();

    @OneToMany(
            mappedBy = "following",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FollowingersEntity> followers = new ArrayList<>();

}
