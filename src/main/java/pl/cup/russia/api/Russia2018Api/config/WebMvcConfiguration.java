package pl.cup.russia.api.Russia2018Api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.cup.russia.api.Russia2018Api.util.jackson.CustomJsonObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new CustomJsonObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();

        jsonMessageConverter.setObjectMapper(objectMapper());
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

}
