package pl.adkr.hsbc.challenge.posting.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class PostEntityTest {

    @Test
    public void shouldAddCreationDateTimeOnEntityCreation() {
        //when
        LocalDateTime now = LocalDateTime.now();
        PostEntity postEntity = new PostEntity(11L, "text1", now);
        PostEntity postEntity1 = new PostEntity(11L, "text1");
        PostEntity postEntity2 = new PostEntity();

        //then
        assertThat(postEntity.getCreateDateTime()).isEqualTo(now);
        assertThat(postEntity1.getCreateDateTime()).isNotNull();
        assertThat(postEntity2.getCreateDateTime()).isNotNull();
    }
}
