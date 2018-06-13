package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.Bet;

import java.util.List;

import static pl.cup.russia.api.Russia2018Api.enums.BetType.WORLD_CUP_WINNER;
import static pl.cup.russia.api.Russia2018Api.util.BetValidator.canBetWorldCupWinner;

@Controller
@RequestMapping("/bets")
public class BetController {

	@Autowired
	private BetService service;

	@Autowired
	private LeagueService leagueService;

	@PostMapping("/winner/{winnerTeamName}")
	public String betWorldCupWinner(@PathVariable String winnerTeamName, RedirectAttributes redirectAttributes) {
		service.createWorldCupWinnerBet(winnerTeamName);

		return getWorldCupWinnerRedirectWithAttributes(redirectAttributes);
	}

	@PutMapping("/winner/{winnerTeamName}")
	public String updateBetWorldCupWinner(@PathVariable String winnerTeamName, RedirectAttributes redirectAttributes) {
		service.updateBetByType(WORLD_CUP_WINNER, new BetValue(winnerTeamName));

		return getWorldCupWinnerRedirectWithAttributes(redirectAttributes);
	}

	@PostMapping("/group")
	public List<Bet> betGroupPromotion(@ModelAttribute("betValue") List<BetValue> betValues) {
		return service.createGroupPromotionBets(betValues);
	}

	@PostMapping("/match")
	public Bet betMatchScore(@ModelAttribute("betValue") BetValue betValue) {
		return service.createMatchScoreBet(betValue);
	}

	private String getWorldCupWinnerRedirectWithAttributes(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("teams", leagueService.selectAllTeams());
		redirectAttributes.addFlashAttribute("userBet", service.selectUserBetByType(WORLD_CUP_WINNER));
		redirectAttributes.addFlashAttribute("canYouBet", canBetWorldCupWinner());

		return StaticHtmlResource.WORLD_CUP_WINNER.getKebabCasedRedirectValue();
	}
}
