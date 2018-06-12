package pl.cup.russia.api.Russia2018Api.controller;

import static java.time.LocalDate.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.Bet;

@Controller
@RequestMapping("/bets")
public class BetController {

	@Autowired
	private BetService betService;

	@Autowired
	private MatchService matchService;

	@PostMapping("/winner/{winnerTeamName}")
	public String betWorldCupWinner(@PathVariable String winnerTeamName, RedirectAttributes redirectAttributes) {
		betService.createWorldCupWinnerBet(winnerTeamName);

		redirectAttributes.addFlashAttribute("todayMatches", matchService.selectMatchesByDate(now()));

		return StaticHtmlResource.WORLD_CUP_WINNER.getKebabCasedRedirectValue();
	}

	@PostMapping("/group")
	public Bet betGroupPromotion(@ModelAttribute("betValue") BetValue betValue) {
		return betService.createGroupPromotionBet(betValue);
	}

	@PostMapping("/match")
	public Bet betMatchScore(@ModelAttribute("betValue") BetValue betValue) {
		return betService.createMatchScoreBet(betValue);
	}

}
