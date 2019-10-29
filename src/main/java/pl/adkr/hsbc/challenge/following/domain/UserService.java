package pl.adkr.hsbc.challenge.following.domain;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adkr.hsbc.challenge.following.entity.followingers.FollowingerRepository;
import pl.adkr.hsbc.challenge.following.entity.user.UserRepository;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FollowingerRepository followingerRepository;

    @Autowired
    public UserService(UserRepository userRepository, FollowingerRepository followingerRepository) {
        this.userRepository = userRepository;
        this.followingerRepository = followingerRepository;
    }

    public User follow(Long userId, Long userToFollow) {
        return null;
    }

    public User unfollow(Long userId, Long userToUnfollow) {
        return null;
    }

    public Set<User> getFollowing(Long userId) {
        return Sets.newHashSet();
    }

    public Set<User> getFollowers(Long userId) {
        return Sets.newHashSet();
    }
}
