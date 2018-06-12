package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.BetType;
import pl.cup.russia.api.Russia2018Api.model.Bet;

import java.util.List;

public interface BetService {

    Bet createWorldCupWinnerBet(String worldCupWinner);

    List<Bet> createGroupPromotionBets(List<BetValue> betValue);

    Bet createMatchScoreBet(BetValue betValue);

    Bet save(Bet bet);

    List<Bet> saveAll(List<Bet> bets);

    Bet selectUserBetByType(BetType type);

    Integer updateBetByType(BetType type, BetValue betValue);
}
