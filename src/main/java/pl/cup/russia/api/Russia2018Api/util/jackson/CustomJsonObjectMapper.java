package pl.cup.russia.api.Russia2018Api.util.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomJsonObjectMapper extends ObjectMapper {

    public CustomJsonObjectMapper() {
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        this.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        this.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.registerModule(new JavaTimeModule());
        this.registerModule(getCustomModule());
        // below configuration needed for property with capital last letter - ex. overall_league_W
        // https://stackoverflow.com/a/44795646
        this.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    private SimpleModule getCustomModule() {
        SimpleModule customModule = new SimpleModule("SimpleModule",
                new Version(1,0,0,null, "pl.cup.russia.api", "Russia2018Api"));

        customModule.addSerializer(Boolean.class, new StringZeroOneBooleanSerializer());
        customModule.addDeserializer(Boolean.class, new StringZeroOneBooleanDeserializer());
        customModule.addDeserializer(Integer.class, new StringHalfTimeIntegerDeserializer());
        customModule.addDeserializer(Integer.class, new StringQuestionMarkIntegerDeserializer());

        return customModule;

    }

}
