package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping
	public String home() {
		return StaticHtmlResource.LOGIN.name();
	}
}
