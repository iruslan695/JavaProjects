package com.example.alphagif.feignClient;

import com.example.alphagif.entity.ExchangeCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangeClient", url = "${conf.exchange.url}")
public interface ExchangeClient {

    String FULL_URL = "/{date}" + "${conf.exchange.format}";
    String BASE = "base";
    String APP_ID = "app_id";

    @RequestMapping(value = FULL_URL, method = RequestMethod.GET)
    ExchangeCourse getExchangeCourse(@PathVariable String date,
                                     @RequestParam(BASE) String base,
                                     @RequestParam(APP_ID) String appId);
}
