package pl.adkr.hsbc.challenge.following.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adkr.hsbc.challenge.following.domain.User;
import pl.adkr.hsbc.challenge.following.domain.UserService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class FollowingRestController {

    private final UserService userService;

    @Autowired
    public FollowingRestController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<User> follow(Long userId, Long userToFollow) {
        userService.follow(userId, userToFollow);
        throw new UnsupportedOperationException("Implement me");
    }

    public ResponseEntity<User> unfollow(Long userId, Long userToUnfollow) {
        userService.unfollow(userId, userToUnfollow);
        throw new UnsupportedOperationException("Implement me");
    }

    public ResponseEntity<Set<User>> getFollowing(Long userId) {
        userService.getFollowing(userId);
        throw new UnsupportedOperationException("Implement me");
    }

    public ResponseEntity<String> getFollowers(Long userId) {
        userService.getFollowers(userId);
        throw new UnsupportedOperationException("Implement me");
    }
}
