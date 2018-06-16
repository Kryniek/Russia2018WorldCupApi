package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.dto.BetValue;
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

    List<Bet> selectUserBetsByType(BetType type);

    Bet selectUserMatchBet(Integer matchId);

    List<Bet> selectMatchBets(Integer matchId);

    Integer updateBetByType(BetType type, BetValue betValue);

    Integer updateBetsByType(BetType type, List<BetValue> betValues);

    void calculatePointsAndUpdateBetRecords();
}
