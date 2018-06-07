package pl.cup.russia.api.Russia2018Api.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomJsonObjectMapper extends ObjectMapper {

    public CustomJsonObjectMapper() {
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        this.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        this.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

}
