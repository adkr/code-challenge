package pl.adkr.hsbc.challenge.posting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.adkr.hsbc.challenge.posting.domain.Post;
import pl.adkr.hsbc.challenge.posting.domain.PostService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
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

    @Test
    public void shouldSaveMessageWith3CharsLong() {
        //given
        String message3 = new String(new char[3]).replace('\0', 'a');

        //when
        Post post3 = postService.storePost(message3, 10L).get();

        //then
        assertThat(post3.getMessage()).hasSize(3);
    }

    @Test
    public void shouldSaveMessageWith140CharsLong() {
        //given
        String message140 = new String(new char[140]).replace('\0', 'a');

        //when
        Post post140 = postService.storePost(message140, 10L).get();

        //then
        assertThat(post140.getMessage()).hasSize(140);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void shouldThrowExceptionWhenStorePostWithMoreThan140Chars() {
        //given
        String message141 = new String(new char[141]).replace('\0', 'a');

        //expect
        assertThrows(TransactionSystemException.class, () -> {
            postService.storePost(message141, 10L);
        });
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void shouldThrowExceptionWhenStorePostWithLessThan3Chars() {
        //given
        String message2 = new String(new char[2]).replace('\0', 'a');

        //expect
        assertThrows(TransactionSystemException.class, () -> {
            postService.storePost(message2, 10L);
        });
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void shouldThrowExceptionWhenStorePostWithoutMessage() {
        //expect
        assertThrows(TransactionSystemException.class, () -> {
            postService.storePost(null, 10L);
        });
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void shouldThrowExceptionWhenStorePostWithoutUserId() {
        //expect
        assertThrows(TransactionSystemException.class, () -> {
            postService.storePost("text", null);
        });
    }
}
