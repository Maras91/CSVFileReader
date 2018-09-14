package com.csv.config;

import com.csv.converters.DataTypeConverter;
import com.csv.converters.JSONConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvertersConfig {
    @Bean
    public DataTypeConverter getDatatypeConverter() {
        return new DataTypeConverter();
    }

    @Bean
    public JSONConverter getJSONConverter() {
        return new JSONConverter();
    }
}
