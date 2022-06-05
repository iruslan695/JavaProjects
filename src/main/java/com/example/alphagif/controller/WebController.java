package com.example.alphagif.controller;

import com.example.alphagif.entity.ExchangeCourse;
import com.example.alphagif.entity.Gif;
import com.example.alphagif.feignClient.GifClient;
import com.example.alphagif.service.ExchangeService;
import com.example.alphagif.service.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/exchangeResults")
public class WebController {

    private final ExchangeService exchangeService;
    private final GifService gifService;
    private static final String GIF_PAGE = "gifPage";
    private static final String GIF_URL = "url";
    private static final String RESULT_URL = "{currency}";

    @GetMapping(RESULT_URL)
    public String getResult(@PathVariable String currency, Model model) {
        String currencyUpper = currency.toUpperCase();
        boolean todayResult = exchangeService.isTodayCourseMoreProfit(currencyUpper);
        String gifUrl = gifService.getGif(todayResult).getData().getEmbedUrl();
        model.addAttribute(GIF_URL, gifUrl);
        return GIF_PAGE;
    }
}
