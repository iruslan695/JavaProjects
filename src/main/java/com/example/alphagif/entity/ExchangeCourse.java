package com.example.alphagif.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeCourse {

    private HashMap<String, Double> rates;
}
