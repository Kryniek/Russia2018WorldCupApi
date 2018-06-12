package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;

import static java.time.LocalDate.now;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private LeagueService leagueService;

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
	public ModelAndView worldCupWinner() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.WORLD_CUP_WINNER.getValue());
		mav.addObject("teams", leagueService.selectAllTeams());

		return mav;
	}

	@GetMapping("/groups-winners")
	public ModelAndView groupsWinners() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.GROUPS_WINNERS.getValue());
		mav.addObject("teamsByGroupName", leagueService.selectTeamsGroupedByLeagueName());

		return mav;
	}

	@GetMapping("/matches")
	public ModelAndView matches() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.MATCHES.getValue());
		mav.addObject("matches", matchService.selectAll());

		return mav;
	}

	@GetMapping("/points")
	public String points() {
		return StaticHtmlResource.POINTS.getValue();
	}

	private ModelAndView getHomeView() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.HOME.getValue());
		mav.addObject("todayMatches", matchService.selectMatchesByDate(now()));

		return mav;
	}
}
