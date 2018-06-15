package pl.cup.russia.api.Russia2018Api.controller;

import static java.time.LocalDate.now;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.GROUP_STAGE_PROMOTION;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.WORLD_CUP_WINNER;
import static pl.cup.russia.api.Russia2018Api.util.BetValidator.canBet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private BetService betService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private MatchService matchService;

	@GetMapping
	public ModelAndView base() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.HOME.getValue());
		mav.addObject("todayMatches", matchService.selectMatchesByDate(now()));

		return mav;
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
		mav.addObject("userBet", betService.selectUserBetByType(WORLD_CUP_WINNER));
		mav.addObject("canYouBet", canBet());

		return mav;
	}

	@GetMapping("/groups-winners")
	public ModelAndView groupsWinners() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.GROUPS_WINNERS.getValue());
		mav.addObject("teamsByGroupName", leagueService.selectTeamsGroupedByLeagueName());
		mav.addObject("userBets", betService.selectUserBetsByType(GROUP_STAGE_PROMOTION));
		mav.addObject("canYouBet", canBet());

		return mav;
	}

	@GetMapping("/matches")
	public ModelAndView matches() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.MATCHES.getValue());
		mav.addObject("matchesByDates", matchService.selectAllMatchesByDates());

		return mav;
	}

	@GetMapping("/points")
	public String points() {
		return StaticHtmlResource.POINTS.getValue();
	}

	@GetMapping("/bet/{matchId}")
	public ModelAndView bet(@PathVariable Integer matchId) {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.BET.getValue());
		mav.addObject("match", matchService.selectByMatchApiId(matchId));
		mav.addObject("userBet", betService.selectUserMatchBet(matchId));
		mav.addObject("canYouBet", canBet());

		return mav;
	}
}
