package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.model.Bet;

public interface BetService {

    Bet createWorldCupWinnerBet(String worldCupWinner);

    Bet createGroupPromotionBet(BetValue betValue);

    Bet createMatchScoreBet(BetValue betValue);

    Bet save(Bet bet);

}
