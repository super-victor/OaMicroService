package com.example.zuulservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
class ZuulServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
