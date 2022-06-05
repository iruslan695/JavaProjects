package com.example.alphagif.service;

import com.example.alphagif.entity.ExchangeCourse;

public interface ExchangeService {

    boolean isTodayCourseMoreProfit(String currency);

    ExchangeCourse getTodayCourse();

    ExchangeCourse getYesterdayCourse();

}
