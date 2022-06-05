package com.example.alphagif.service;

import com.example.alphagif.entity.Gif;
import com.example.alphagif.feignClient.GifClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GifServiceImpl implements GifService {

    private final GifClient gifClient;

    @Value("${conf.gif.richTag}")
    private String RICH_TAG;
    @Value("${conf.gif.brokeTag}")
    private String BROKE_TAG;
    @Value("${conf.gif.apiKey}")
    private String API_KEY;

    @Override
    public Gif getGif(boolean result) {
        Gif gif = result ? gifClient.getGif(RICH_TAG,API_KEY) : gifClient.getGif(BROKE_TAG,API_KEY);
        return gif;
    }
}
