package pl.cup.russia.api.Russia2018Api.controller;

import static java.time.LocalDate.now;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.WORLD_CUP_WINNER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
	private BetService service;

	@Autowired
	private MatchService matchService;

	@PostMapping("/winner/{winnerTeamName}")
	public String betWorldCupWinner(@PathVariable String winnerTeamName, RedirectAttributes redirectAttributes) {
		service.createWorldCupWinnerBet(winnerTeamName);

		redirectAttributes.addFlashAttribute("todayMatches", matchService.selectMatchesByDate(now()));

		return StaticHtmlResource.WORLD_CUP_WINNER.getKebabCasedRedirectValue();
	}

    @PutMapping("/winner/{winnerTeamName}")
    public String updateBetWorldCupWinner(@PathVariable String winnerTeamName, RedirectAttributes redirectAttributes) {
        service.updateBetByType(WORLD_CUP_WINNER, new BetValue(winnerTeamName));

        redirectAttributes.addFlashAttribute("todayMatches", matchService.selectMatchesByDate(now()));

        return StaticHtmlResource.WORLD_CUP_WINNER.getKebabCasedRedirectValue();
    }

    @PostMapping("/group")
    public Bet betGroupPromotion(@ModelAttribute("betValue") BetValue betValue) {
        return service.createGroupPromotionBet(betValue);
    }

	@PostMapping("/match")
	public Bet betMatchScore(@ModelAttribute("betValue") BetValue betValue) {
		return service.createMatchScoreBet(betValue);
	}

}
