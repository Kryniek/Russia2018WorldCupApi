package pl.cup.russia.api.Russia2018Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Russia2018ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Russia2018ApiApplication.class, args);
	}

}
