package pl.cup.russia.api.Russia2018Api.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StringZeroOneBooleanSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean bool, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(bool ? "1" : "0");
    }
}
