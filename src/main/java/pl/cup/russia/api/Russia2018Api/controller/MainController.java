package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private MatchService matchService;

	@GetMapping()
	public ModelAndView base() {
		return getHomeView();
	}

	@GetMapping("/login")
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

	@GetMapping("/matches")
	public ModelAndView matches() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.MATCHES.getValue());
		mav.addObject("matches", matchService.selectAll());

		return mav;
	}

	@GetMapping("/home")
	public ModelAndView home() {
		return getHomeView();
	}

	private ModelAndView getHomeView() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.HOME.getValue());
		mav.addObject("todayMatches", matchService.selectMatchesByDate(LocalDate.now()));

		return mav;
	}
}
