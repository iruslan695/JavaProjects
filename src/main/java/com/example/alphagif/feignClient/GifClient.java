package com.example.alphagif.feignClient;

import com.example.alphagif.entity.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "gifClient", url = "${conf.gif.url}")
public interface GifClient {

    String TAG = "tag";
    String API_KEY = "api_key";

    @GetMapping()
    Gif getGif(@RequestParam(TAG) String tag, @RequestParam(API_KEY) String apiKey);
}
