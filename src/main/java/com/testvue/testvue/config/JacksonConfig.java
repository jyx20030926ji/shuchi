package com.testvue.testvue.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.testvue.testvue.localserializer.CustomLocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class JacksonConfig {


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        mapper.registerModule(module);

        return mapper;
    }





}
