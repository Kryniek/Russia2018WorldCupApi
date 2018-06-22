package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.definition.ResultService;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.Match;

import static java.time.LocalDate.now;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.GROUP_STAGE_PROMOTION;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.WORLD_CUP_WINNER;
import static pl.cup.russia.api.Russia2018Api.util.BetValidator.canBet;
import static pl.cup.russia.api.Russia2018Api.util.BetValidator.canBetMatch;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private BetService betService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private MatchService matchService;

	@Autowired
	private ResultService resultService;
	
	@Autowired
	private UserService userService;

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
		Match match = matchService.selectByMatchApiId(matchId);
		mav.addObject("match", match);
		mav.addObject("userBet", betService.selectUserMatchBet(matchId));
		mav.addObject("canYouBet", canBetMatch(match.getDate(), match.getTime()));

		return mav;
	}

	@GetMapping("/results")
	public ModelAndView results() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.RESULTS.getValue());
		mav.addObject("paidUserResults", resultService.getResultsForPaidUsers());
		mav.addObject("nonPaidUserResults", resultService.getResultsForNonPaidUsers());

		return mav;
	}

	@GetMapping("/user-bets")
	public ModelAndView userBets() {
		ModelAndView mav = new ModelAndView(StaticHtmlResource.USER_BETS.getValue());
		mav.addObject("userBets", userService.getUserBets());

		return mav;
	}
}
