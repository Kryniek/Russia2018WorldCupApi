package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.BetType;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.repository.BetRepository;

import static pl.cup.russia.api.Russia2018Api.enums.BetStatus.OPENED;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.*;
import static pl.cup.russia.api.Russia2018Api.util.SecurityUtil.getLoggedInUser;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository repository;

    @Override
    public Bet createWorldCupWinnerBet(String worldCupWinner) {
        return save(getBet(new BetValue(worldCupWinner), WORLD_CUP_WINNER));
    }

    @Override
    public Bet createGroupPromotionBet(BetValue betValue) {
        return save(getBet(betValue, GROUP_STAGE_PROMOTION));
    }


    @Override
    public Bet createMatchScoreBet(BetValue betValue) {
        return save(getBet(betValue, MATCH_RESULT));
    }

    private Bet getBet(BetValue betValue, BetType groupStagePromotion) {
        Bet bet = new Bet();
        bet.setValue(betValue);
        bet.setStatus(OPENED);
        bet.setType(groupStagePromotion);
        bet.setUsername(getLoggedInUser());

        return bet;
    }

    @Override
    public Bet save(Bet bet) {
        return repository.save(bet);
    }

}
