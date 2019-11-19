package com.dongtian.shopcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@SpringBootApplication
public class ShopCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCommonApplication.class, args);
    }

}
