package com.dongtian.shopmobileweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShopMobileWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopMobileWebApplication.class, args);
    }

}
