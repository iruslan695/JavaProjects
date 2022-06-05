package com.example.alphagif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphaGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaGifApplication.class, args);
    }
}