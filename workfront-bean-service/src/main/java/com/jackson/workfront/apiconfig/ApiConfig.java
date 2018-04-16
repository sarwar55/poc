package com.jackson.workfront.apiconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sarwar on 4/14/18.
 */

@Configuration
public class ApiConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ObjectWriter objectWriter( ObjectMapper objectMapper){
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

}
