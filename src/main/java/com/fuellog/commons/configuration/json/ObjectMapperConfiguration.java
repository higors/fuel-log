package com.fuellog.commons.configuration.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ObjectMapperConfiguration {

    public ObjectMapper getObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();

        final SimpleModule dateModule = dateModule();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.registerModule(dateModule);

        return mapper;
    }

    private SimpleModule dateModule() {
        final SimpleModule dateModule = new SimpleModule("DateModule");
        dateModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        dateModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

        dateModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        dateModule.addSerializer(LocalDate.class, new LocalDateSerializer());

        dateModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        dateModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());

        dateModule.addDeserializer(Instant.class, new InstantDeserializer());
        dateModule.addSerializer(Instant.class, new InstantSerializer());
        return dateModule;
    }

}
