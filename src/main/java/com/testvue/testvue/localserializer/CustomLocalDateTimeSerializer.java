package com.testvue.testvue.localserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CustomLocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws  IOException {
        gen.writeString(formatter.format(value));
    }
}