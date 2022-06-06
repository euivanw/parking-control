package dev.ivanwilhelm.parkingcontrol.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {
    public static final String DATE_TIME_FORMAT = "yyy-MM-dd'T'HH:mm:ss'Z'";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static LocalDateTimeSerializer LOCAL_DATE_TIME_SERIALIZER = new LocalDateTimeSerializer(DATE_TIME_FORMATTER);

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        var javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LOCAL_DATE_TIME_SERIALIZER);

        return new ObjectMapper().registerModule(javaTimeModule);
    }
}
