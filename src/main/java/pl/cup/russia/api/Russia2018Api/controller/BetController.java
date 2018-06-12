package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.model.Bet;

import static pl.cup.russia.api.Russia2018Api.enums.BetType.WORLD_CUP_WINNER;

@RestController
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService service;

    @PostMapping("/winner/{winnerTeamName}")
    public Bet betWorldCupWinner(@PathVariable String winnerTeamName) {
        return service.createWorldCupWinnerBet(winnerTeamName);
    }

    @PutMapping("/winner/{winnerTeamName}")
    public Integer updateBetWorldCupWinner(@PathVariable String winnerTeamName) {
        return service.updateBetByType(WORLD_CUP_WINNER, new BetValue(winnerTeamName));
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
