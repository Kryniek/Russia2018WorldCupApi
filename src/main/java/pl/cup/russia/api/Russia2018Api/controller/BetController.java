package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.DBOperation;
import pl.cup.russia.api.Russia2018Api.enums.StaticHtmlResource;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.util.jackson.CustomJsonObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.cup.russia.api.Russia2018Api.enums.BetType.*;
import static pl.cup.russia.api.Russia2018Api.util.BetValidator.canBetWorldCupWinnerAndGroupWinners;

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

		return getWorldCupWinnerRedirectWithAttributes(redirectAttributes, DBOperation.INSERTED);
	}

	@PutMapping("/winner/{winnerTeamName}")
	public String updateBetWorldCupWinner(@PathVariable String winnerTeamName, RedirectAttributes redirectAttributes) {
		service.updateBetByType(WORLD_CUP_WINNER, new BetValue(winnerTeamName));

		return getWorldCupWinnerRedirectWithAttributes(redirectAttributes, DBOperation.UPDATED);
	}

	@PostMapping("/groups")
	@ResponseBody
	public List<Bet> betGroupPromotion(@RequestBody BetValue betValue) {
//		System.out.println(betValues.stream().map(BetValue::getGroupName).collect(Collectors.toList()));
//		System.out.println(betValue.getGroupName());
//		CustomJsonObjectMapper mapper = new CustomJsonObjectMapper();
//		System.out.println((mapper.readValue(betValue, BetValue.class)));
		System.out.println(betValue);
		List<Bet> testBets = new ArrayList<>();
//		for (BetValue value : betValues) {
//			Bet bet = new Bet();
//			BetValue bv = new BetValue();
//			bv.setGroupName(value.getGroupName());
//			bv.setFirstPlace(value.getFirstPlace());
//			bv.setSecondPlace(value.getSecondPlace());
//			bet.setValue(bv);
//
//			testBets.add(bet);
//		}
		return testBets;
		//return service.createGroupPromotionBets(betValues);
	}

	@PutMapping("/groups")
	public Integer updateBetGroupPromotion(@ModelAttribute("betValues") List<BetValue> betValues) {
		return service.updateBetsByType(GROUP_STAGE_PROMOTION, betValues);
	}

	@PostMapping("/match")
	public Bet betMatchScore(@ModelAttribute("betValue") BetValue betValue) {
		return service.createMatchScoreBet(betValue);
	}

	@PutMapping("/match")
    public Integer updateBetMatchScore(@ModelAttribute("betValue") BetValue betValue) {
        return service.updateBetByType(MATCH_RESULT, betValue);
    }

	private String getWorldCupWinnerRedirectWithAttributes(RedirectAttributes redirectAttributes,
			DBOperation operation) {
		redirectAttributes.addFlashAttribute("teams", leagueService.selectAllTeams());
		redirectAttributes.addFlashAttribute("userBet", service.selectUserBetByType(WORLD_CUP_WINNER));
		redirectAttributes.addFlashAttribute("canYouBet", canBetWorldCupWinnerAndGroupWinners());
		redirectAttributes.addFlashAttribute("operation", operation.name());

		return StaticHtmlResource.WORLD_CUP_WINNER.getKebabCasedRedirectValue();
	}
}
