package com.fuellog.commons.configuration.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

public class InstantSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(final Instant temporal, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider)
        throws IOException {
        if (temporal == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(temporal.toString());
        }
    }

}
