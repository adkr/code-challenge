package pl.adkr.hsbc.challenge.posting.entity;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldReturnPostsInReverseOrderOfCreation() {
        //given
        LocalDateTime now = LocalDateTime.now();
        PostEntity postEntity1 = new PostEntity(1L, "text1", now);
        PostEntity postEntity2 = new PostEntity(1L, "text2", now.plusSeconds(1L));
        PostEntity postEntity3 = new PostEntity(1L, "text3", now.plusSeconds(2L));
        postRepository.saveAll(Lists.list(postEntity1, postEntity2, postEntity3));

        //when
        Collection<PostEntity> posts = postRepository.findAllByUserIdOrderByCreateDateTimeDesc(1L);

        //then
        assertThat(posts).containsExactly(postEntity3, postEntity2, postEntity1);
    }
}
