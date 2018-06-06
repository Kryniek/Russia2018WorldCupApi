package pl.cup.russia.api.Russia2018Api.external.api.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.service.footballApiService.UrlParseException;

@RestController
@RequestMapping("/test")
public class FootballApiController {

	@Autowired
	private FootballApiService footballApiService;

	@GetMapping
	public JSONObject test() {
		return footballApiService.getLeaguesJSONObject();
	}

	@GetMapping("/a")
	public String test2() {
		throw new UrlParseException();
	}
}
