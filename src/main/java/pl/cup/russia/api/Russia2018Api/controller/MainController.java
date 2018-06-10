package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping
	public String login() {
		return StaticHtmlResource.LOGIN.getValue();
	}

	@GetMapping("/register")
	public String register() {
		return StaticHtmlResource.REGISTER.getValue();
	}

	@GetMapping("/world-cup-winner")
	public String worldCupWinner() {
		return StaticHtmlResource.WORLD_CUP_WINNER.getValue();
	}

	@GetMapping("/groups-winners")
	public String groupsWinners() {
		return StaticHtmlResource.GROUPS_WINNERS.getValue();
	}
}
