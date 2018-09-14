package com.csv.config;

import com.csv.CSVFileProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CSVFileProcessorConfig {
    @Bean
    public CSVFileProcessor getCSVFileProcessor() {
        return new CSVFileProcessor();
    }
}
