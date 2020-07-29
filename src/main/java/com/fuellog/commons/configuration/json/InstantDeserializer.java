package com.fuellog.commons.configuration.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;

public class InstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(final JsonParser jsonParser,
                               final DeserializationContext deserializationCtx) throws IOException {
        final ObjectCodec oc = jsonParser.getCodec();
        final String content = oc.readValue(jsonParser, String.class);

        if (content == null) {
            return null;
        }

        return ZonedDateTime.parse(content).toInstant();
    }
}
