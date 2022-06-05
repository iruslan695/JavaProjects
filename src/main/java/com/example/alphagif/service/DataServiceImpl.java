package com.example.alphagif.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DataServiceImpl implements DateService{

    @Override
    public String getTodayDate() {
        return LocalDate.now().toString();
    }

    @Override
    public String getYesterdayDate() {
        return LocalDate.now().minusDays(1).toString();
    }
}
