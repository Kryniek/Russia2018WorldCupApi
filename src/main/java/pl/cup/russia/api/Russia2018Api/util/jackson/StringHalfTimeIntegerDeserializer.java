package pl.cup.russia.api.Russia2018Api.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import static java.lang.Integer.valueOf;

public class StringHalfTimeIntegerDeserializer extends JsonDeserializer<Integer> {

    private static final String HALF = "half";
    private static final String TIME = "time";

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return (jsonParser.getText().toLowerCase().equals(HALF) || jsonParser.getText().toLowerCase().equals(TIME))
                ? valueOf(0) : valueOf(jsonParser.getText());
    }

}
