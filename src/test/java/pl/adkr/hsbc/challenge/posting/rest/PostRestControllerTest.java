package pl.adkr.hsbc.challenge.posting.rest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.adkr.hsbc.challenge.posting.domain.Post;
import pl.adkr.hsbc.challenge.posting.domain.PostService;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;

@WebMvcTest(PostRestController.class)
public class PostRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Test
    public void shouldReturnOneResultAndOkStatus() throws Exception {
        //given
        Long userId = 1L;
        String msg = "text";
        Post expectedPost = Post.builder().message(msg).userId(userId).build();

        Mockito.when(postService.getPost(anyLong())).thenReturn(ofNullable(expectedPost));

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/message/{id}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //then
        JSONObject returnedJson = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(returnedJson.getString("message"), is(equalTo(expectedPost.getMessage())));
        assertThat(returnedJson.getLong("userId"), is(equalTo(expectedPost.getUserId())));
    }

    @Test
    public void shouldReturnNoResultsAndNotFoundStatus() throws Exception {
        //given
        Mockito.when(postService.getPost(anyLong())).thenReturn(Optional.empty());

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/message/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getContentAsString(), emptyString());
        assertThat(mvcResult.getResponse().getStatus(), is(equalTo(HttpStatus.NOT_FOUND.value())));
    }

    @Test
    public void shouldNotAcceptMediaTypeDifferentThanJson() throws Exception {
        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/message/{id}", 1)
                .accept(MediaType.TEXT_PLAIN))
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getStatus(), is(equalTo(HttpStatus.NOT_ACCEPTABLE.value())));
    }
}
