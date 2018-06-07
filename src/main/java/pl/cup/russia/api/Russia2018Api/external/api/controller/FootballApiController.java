package pl.cup.russia.api.Russia2018Api.external.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;

import java.util.List;

@RestController
@RequestMapping("/external-api")
public class FootballApiController {

	@Autowired
	private FootballApiService footballApiService;

	@GetMapping("/leagues")
	public List<ApiLeague> getExternalApiLeagues() {
		return footballApiService.getApiLeagues();
	}
}
