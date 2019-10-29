package pl.adkr.hsbc.challenge.following.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adkr.hsbc.challenge.following.domain.User;
import pl.adkr.hsbc.challenge.following.domain.UserService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@Api(value = "Following management")
public class FollowingRestController {

    private static final String USER_ID = "userId";
    private static final String FOLLOWEE_ID = "followeeId";

    private final UserService userService;

    @Autowired
    public FollowingRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{" + USER_ID + "}/follow/{" + FOLLOWEE_ID + "}")
    @ApiOperation(value = "Add a followee")
    public ResponseEntity<User> follow(@PathVariable(USER_ID) Long userId, @PathVariable(FOLLOWEE_ID) Long userToFollow) {
        userService.follow(userId, userToFollow);
        throw new UnsupportedOperationException("Implement me");
    }

    @DeleteMapping("/{" + USER_ID + "}/follow/{" + FOLLOWEE_ID + "}")
    @ApiOperation(value = "Delete a followee")
    public ResponseEntity<User> unfollow(@PathVariable(USER_ID) Long userId, @PathVariable(FOLLOWEE_ID) Long userToUnfollow) {
        userService.unfollow(userId, userToUnfollow);
        throw new UnsupportedOperationException("Implement me");
    }


    @GetMapping("/{" + USER_ID + "}/follow/")
    @ApiOperation(value = "Get a set of following users")
    public ResponseEntity<Set<User>> getFollowing(@PathVariable(USER_ID) Long userId) {
        userService.getFollowing(userId);
        throw new UnsupportedOperationException("Implement me");
    }

    @GetMapping("/follow/{" + USER_ID +"}")
    @ApiOperation(value = "Get a set of followers")
    public ResponseEntity<String> getFollowers(@PathVariable(USER_ID) Long userId) {
        userService.getFollowers(userId);
        throw new UnsupportedOperationException("Implement me");
    }
}
