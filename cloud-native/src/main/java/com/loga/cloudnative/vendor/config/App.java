package com.loga.cloudnative.vendor.config;
import com.loga.cloudnative.vendor.io.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App {

    @Autowired
    private Logger logger;

    @Bean
    CommandLineRunner init() {
        return  args -> {
            logger.info("LoGA Cloud Native Initialization ---");
        };
    }
}
