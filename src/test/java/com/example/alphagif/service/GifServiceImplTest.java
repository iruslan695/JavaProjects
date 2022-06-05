package com.example.alphagif.service;

import com.example.alphagif.entity.Gif;
import com.example.alphagif.entity.GifData;
import com.example.alphagif.feignClient.GifClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GifServiceImplTest {

    @Autowired
    private GifService gifService;

    @Value("${conf.gif.richTag}")
    private String RICH_TAG;
    @Value("${conf.gif.brokeTag}")
    private String BROKE_TAG;
    @Value("${conf.gif.apiKey}")
    private String API_KEY;

    @MockBean
    private GifClient gifClient;

    @Test
    void getGifUrl() {
        GifData gifDataBroke = new GifData("as",BROKE_TAG);
        GifData gifDataRich = new GifData("as",RICH_TAG);
        Gif gifRich = new Gif(gifDataRich);
        Gif gifBroke = new Gif(gifDataBroke);
        Mockito.doReturn(gifBroke).when(gifClient).getGif(BROKE_TAG,API_KEY);
        Mockito.doReturn(gifRich).when(gifClient).getGif(RICH_TAG,API_KEY);
       Gif gifRichResult = gifService.getGif(true);
        assertTrue(gifRichResult.getData().getSlug().equals(RICH_TAG));
        Gif gifBrokeResult = gifService.getGif(false);
        assertTrue(gifBrokeResult.getData().getSlug().equals(BROKE_TAG));
    }
}