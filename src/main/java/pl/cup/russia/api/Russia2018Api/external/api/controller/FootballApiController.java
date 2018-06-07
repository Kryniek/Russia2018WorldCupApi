package pl.cup.russia.api.Russia2018Api.external.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;

@RestController
@RequestMapping("/test")
public class FootballApiController {

	@Autowired
	private FootballApiService footballApiService;

	@GetMapping
	public String test() {
		return footballApiService.getLeaguesJSONArray().toString();
	}
}
