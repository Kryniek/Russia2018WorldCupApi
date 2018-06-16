package pl.cup.russia.api.Russia2018Api.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.Integer.valueOf;

public class StringQuestionMarkIntegerDeserializer extends JsonDeserializer<Integer> {

    private static final String QUESTION_MARK = "?";

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String textValue = jsonParser.getText();

        if (isNullOrEmpty(textValue) || textValue.equals(QUESTION_MARK))
            return valueOf(0);
        else
            return valueOf(textValue);
    }

}
