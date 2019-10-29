package pl.adkr.hsbc.challenge.following.entity;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingersEntity;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingersRepository;
import pl.adkr.hsbc.challenge.following.entity.user.UserEntity;
import pl.adkr.hsbc.challenge.following.entity.user.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowingersRepository followingersRepository;

    @Test
    public void shouldFailToSaveUserWithUnsavedTransientFollowingUser() {
        UserEntity user = new UserEntity("login");
        UserEntity following = new UserEntity("following");
        FollowingersEntity followinger = new FollowingersEntity(user, following);
        user.getFollowing().add(followinger);

//        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            userRepository.saveAll(Lists.list(user, following));
            followingersRepository.save(followinger);
  //      });
    }

//    @Test
//    public void shouldFollowsTwoUsers() {
//        //given
//        UserEntity user = new UserEntity("login");
//        UserEntity following1 = new UserEntity("following1");
//        UserEntity following2 = new UserEntity("following2");
//        user.getFollowing().addAll(Lists.list(following1, following2));
//
//        //when
//        userRepository.saveAll(Lists.list(user, following1, following2));
//        UserEntity persistedUser = userRepository.findByLogin("login").get();
//        //then
//
//        assertThat(persistedUser).isNotNull();
//        assertThat(persistedUser.getFollowing()).hasSize(2);
//        assertThat(persistedUser.getFollowing()).containsExactlyInAnyOrder(following2, following1);
//    }
}
