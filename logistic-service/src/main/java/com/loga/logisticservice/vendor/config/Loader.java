package com.loga.logisticservice.vendor.config;

import com.loga.logisticservice.vendor.io.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Loader {

    @Bean
    CommandLineRunner init() {
        return args -> {
            Log.info("Logistic Service Initialized !!!");
        };
    }
}

