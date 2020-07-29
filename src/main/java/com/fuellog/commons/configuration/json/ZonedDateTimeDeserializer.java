package com.fuellog.commons.configuration.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;

public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(final JsonParser jsonParser,
                                     final DeserializationContext deserializationCtx) throws IOException {
        final ObjectCodec oc = jsonParser.getCodec();
        final String content = oc.readValue(jsonParser, String.class);

        if (content == null) {
            return null;
        }

        return ZonedDateTime.parse(content);
    }
}
