package com.example.alphagif.service;

import com.example.alphagif.entity.ExchangeCourse;
import com.example.alphagif.feignClient.ExchangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeClient exchangeClient;
    private final DateService dateService;

    @Value("${conf.exchange.base}")
    private String base;
    @Value("${conf.exchange.appId}")
    private String appId;

    public ExchangeCourse getTodayCourse() {
        String todayDate = dateService.getTodayDate();
        ExchangeCourse exchangeCourse = exchangeClient.getExchangeCourse(todayDate, base, appId);
        return exchangeCourse;
    }

    public ExchangeCourse getYesterdayCourse() {
        String yesterdayDate = dateService.getYesterdayDate();
        ExchangeCourse exchangeCourse = exchangeClient.getExchangeCourse(yesterdayDate, base, appId);
        return exchangeCourse;
    }

    @Override
    public boolean isTodayCourseMoreProfit(String currency) {
        ExchangeCourse todayCourse = getTodayCourse();
        ExchangeCourse yesterdayCourse = getYesterdayCourse();
        double todayRate = todayCourse.getRates().get(currency);
        double yesterdayRate = yesterdayCourse.getRates().get(currency);
        return todayRate - yesterdayRate > 0 ? true : false;
    }
}
