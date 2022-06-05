package com.example.alphagif;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.StringContains.containsString;

@AutoConfigureMockMvc
@SpringBootTest
class AlphaGifApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testRUB() throws Exception {
        this.mockMvc.perform(get("/exchangeResults/rub"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("https://giphy.com/embed/")));
    }

    @Test
    void testError() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
