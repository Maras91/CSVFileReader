package com.csv.config;

import com.csv.network.PackageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PackageSenderConfig {


    @Bean
    public PackageSender packageSender () {
        return new PackageSender();
    }
}
