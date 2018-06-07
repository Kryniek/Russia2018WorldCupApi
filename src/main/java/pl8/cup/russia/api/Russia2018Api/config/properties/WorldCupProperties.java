package pl8.cup.russia.api.Russia2018Api.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "worldcup")
@Getter
@Setter
public class WorldCupProperties {

    private String apiKey;

}
