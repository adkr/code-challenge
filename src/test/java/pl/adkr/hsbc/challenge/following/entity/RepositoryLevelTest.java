package pl.adkr.hsbc.challenge.following.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingerEntity;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingerEntityId;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingerRepository;
import pl.adkr.hsbc.challenge.following.entity.user.UserEntity;
import pl.adkr.hsbc.challenge.following.entity.user.UserRepository;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RepositoryLevelTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowingerRepository followingerRepository;

    @Test
    public void shouldPersistUserFollowerAndFollowingerOnlyViaUserRepository() {
        //given
        UserEntity user = new UserEntity("login");
        UserEntity following = new UserEntity("following");
        FollowingerEntity followinger = new FollowingerEntity(user, following);
        user.getFollowing().add(followinger);

        //when
        userRepository.save(user);

        //then
        UserEntity ue1 = userRepository.findByLogin("login").get();
        UserEntity ue2 = userRepository.findByLogin("following").get();
        FollowingerEntity fe = followingerRepository.findById(new FollowingerEntityId(ue1.getId(), ue2.getId())).get();

        //expect
        assertThat(ue1).isNotNull();
        assertThat(ue2).isNotNull();
        assertThat(fe).isNotNull();
    }

    @Test
    public void shouldUsr2FindFollowerWhenIsFollowedByUsr1() {
        //given
        UserEntity usr1 = new UserEntity("follower");
        UserEntity usr2 = new UserEntity("followed");
        UserEntity usr3 = new UserEntity("other");
        FollowingerEntity followinger = new FollowingerEntity(usr1, usr2);
        usr1.getFollowing().add(followinger);
        FollowingerEntity unrelatedFollowinger = new FollowingerEntity(usr3, usr1);
        usr3.getFollowing().add(unrelatedFollowinger);
        userRepository.save(usr1);

        //when
        UserEntity followed = userRepository.findByLogin("followed").get();
        Set<FollowingerEntity> followers = followingerRepository.findAllByIdFollowingId(followed.getId());

        //then
        assertThat(followers).hasSize(1);
        assertThat(followers.iterator().next().getFollower().getLogin()).isEqualTo(usr1.getLogin());
    }

    @Test
    public void shouldHaveTwoFollowers() {
        //given
        UserEntity usr1 = new UserEntity("follower1");
        UserEntity usr11 = new UserEntity("follower2");
        UserEntity usr2 = new UserEntity("followed");
        usr2.getFollowers().add(new FollowingerEntity(usr1, usr2));
        usr2.getFollowers().add(new FollowingerEntity(usr11, usr2));
        userRepository.save(usr2);

        //when
        UserEntity followed = userRepository.findByLogin("followed").get();
        List<FollowingerEntity> followers = followed.getFollowers();

        //then
        assertThat(followers).hasSize(2);
    }
}
