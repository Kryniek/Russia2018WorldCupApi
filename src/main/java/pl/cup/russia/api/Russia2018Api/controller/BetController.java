package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.Bet;

import java.util.List;

import static java.time.LocalDate.now;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.GROUP_STAGE_PROMOTION;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.WORLD_CUP_WINNER;

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
    public Integer updateBetWorldCupWinner(@PathVariable String winnerTeamName) {
        return service.updateBetByType(WORLD_CUP_WINNER, new BetValue(winnerTeamName));
    }

    @PostMapping("/group")
    public List<Bet> betGroupPromotion(@ModelAttribute("betValues") List<BetValue> betValues) {
        return service.createGroupPromotionBets(betValues);
    }

    @PutMapping("/group")
	public Integer updateBetGroupPromotion(@ModelAttribute("betValues") List<BetValue> betValues) {
		return service.updateBetsByType(GROUP_STAGE_PROMOTION, betValues);
	}

	@PostMapping("/match")
	public Bet betMatchScore(@ModelAttribute("betValue") BetValue betValue) {
		return service.createMatchScoreBet(betValue);
	}

}
