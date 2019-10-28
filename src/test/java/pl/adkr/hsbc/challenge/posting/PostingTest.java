package pl.adkr.hsbc.challenge.posting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.adkr.hsbc.challenge.posting.domain.Post;
import pl.adkr.hsbc.challenge.posting.domain.PostService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostingTest {

    @Autowired
    private PostService postService;

    @Test
    public void shouldAddAndReadPostSuccessfully() {
        //when
        Post storedPost = postService.storePost("text", 10L).get();
        Post readPost = postService.getPost(storedPost.getId()).get();

        //then
        assertThat(storedPost.getId()).isEqualTo(readPost.getId());
        assertThat(storedPost).isEqualTo(readPost);
    }

    @Test
    public void shouldGetPostsSuccessfully() {
        //given
        postService.storePost("text", 10L);
        Post somePost = postService.storePost("text1", 10L).get();
        postService.storePost("text2", 10L);
        postService.storePost("text3", 20L);

        //when
        List<Post> posts = postService.getPostsForUser(10L);

        //then
        assertThat(posts).hasSize(3);
        assertThat(posts).contains(somePost);
    }

}
