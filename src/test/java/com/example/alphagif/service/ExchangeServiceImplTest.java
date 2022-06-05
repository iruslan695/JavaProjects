package com.example.alphagif.service;

import com.example.alphagif.entity.ExchangeCourse;
import com.example.alphagif.feignClient.ExchangeClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExchangeServiceImplTest {

    @MockBean
    private DateService dateService;

    @Autowired
    private ExchangeService exchangeService;

    @MockBean
    private ExchangeClient exchangeClient;

    @Value("${conf.exchange.base}")
    private String base;
    @Value("${conf.exchange.appId}")
    private String appId;

    @Test
    void isTodayCourseMoreProfitTest() {
        HashMap<String, Double> todayRates = new HashMap<>();
        todayRates.put("RUB", 60.0);
        ExchangeCourse exchangeCourseToday = new ExchangeCourse(todayRates);
        Mockito.doReturn(exchangeCourseToday).when(exchangeClient).getExchangeCourse(
                ArgumentMatchers.eq(LocalDate.now().toString()),
                ArgumentMatchers.eq(base),
                ArgumentMatchers.eq(appId)
        );
        Mockito.doReturn(LocalDate.now().toString()).when(dateService).getTodayDate();

        HashMap<String, Double> yesterdayRates = new HashMap<>();
        yesterdayRates.put("RUB", 59.0);
        ExchangeCourse exchangeCourseYesterday = new ExchangeCourse(yesterdayRates);
        Mockito.doReturn(exchangeCourseYesterday).when(exchangeClient).getExchangeCourse(
                ArgumentMatchers.eq(LocalDate.now().minusDays(1).toString()),
                ArgumentMatchers.eq(base),
                ArgumentMatchers.eq(appId)
        );
        Mockito.doReturn(LocalDate.now().minusDays(1).toString()).when(dateService).getYesterdayDate();
        assertTrue(exchangeService.isTodayCourseMoreProfit("RUB"));
        todayRates.put("RUB", 58.0);
        assertFalse(exchangeService.isTodayCourseMoreProfit("RUB"));
    }

    @Test
    void getTodayCourseTest() throws IOException {
        HashMap<String, Double> todayRates = new HashMap<>();
        todayRates.put("RUB", 60.0);
        ExchangeCourse exchangeCourseToday = new ExchangeCourse(todayRates);
        Mockito.doReturn(exchangeCourseToday).when(exchangeClient).getExchangeCourse(
                ArgumentMatchers.eq(LocalDate.now().toString()),
                ArgumentMatchers.eq(base),
                ArgumentMatchers.eq(appId)
        );
        Mockito.doReturn(LocalDate.now().toString()).when(dateService).getTodayDate();
        ExchangeCourse exchangeCourse = exchangeService.getTodayCourse();
        assertNotNull(exchangeCourse);
        Mockito.verify(dateService, Mockito.times(1)).getTodayDate();
        Mockito.verify(exchangeClient, Mockito.times(1))
                .getExchangeCourse(LocalDate.now().toString(), base, appId);
    }


    @Test
    void getYesterdayCourseTest() {
        HashMap<String, Double> yesterdayRates = new HashMap<>();
        yesterdayRates.put("RUB", 61.0);
        ExchangeCourse exchangeCourseYesterday = new ExchangeCourse(yesterdayRates);
        Mockito.doReturn(exchangeCourseYesterday).when(exchangeClient).getExchangeCourse(
                ArgumentMatchers.eq(LocalDate.now().minusDays(1).toString()),
                ArgumentMatchers.eq(base),
                ArgumentMatchers.eq(appId)
        );
        Mockito.doReturn(LocalDate.now().minusDays(1).toString()).when(dateService).getYesterdayDate();
        ExchangeCourse exchangeCourse = exchangeService.getYesterdayCourse();
        assertNotNull(exchangeCourse);
        Mockito.verify(dateService, Mockito.times(1)).getYesterdayDate();
        Mockito.verify(exchangeClient, Mockito.times(1))
                .getExchangeCourse(LocalDate.now().minusDays(1).toString(), base, appId);
    }
}