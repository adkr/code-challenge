package pl.adkr.hsbc.challenge.post;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PostRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReceiveMessageFromEndpoint() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/message/")).andExpect(status().is2xxSuccessful()).andReturn();
        MatcherAssert.assertThat(mvcResult, is(equalTo("as")));
    }
}
