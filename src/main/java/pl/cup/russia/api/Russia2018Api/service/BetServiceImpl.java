package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.BetType;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.repository.BetRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;
import static pl.cup.russia.api.Russia2018Api.enums.BetStatus.OPENED;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.*;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.BETS;
import static pl.cup.russia.api.Russia2018Api.util.SecurityUtil.getLoggedInUser;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Bet createWorldCupWinnerBet(String worldCupWinner) {
        return save(getBet(new BetValue(worldCupWinner), WORLD_CUP_WINNER));
    }

    @Override
    public List<Bet> createGroupPromotionBets(List<BetValue> betValues) {
        List<Bet> bets = new ArrayList<>();

        for (BetValue value : betValues) {
            bets.add(getBet(value, GROUP_STAGE_PROMOTION));
        }

        return saveAll(bets);
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

    @Override
    public List<Bet> saveAll(List<Bet> bets) {
        return repository.saveAll(bets);
    }

    @Override
    public Bet selectUserBetByType(BetType type) {
        return repository.findBetByTypeAndUsername(type.name(), getLoggedInUser());
    }

    @Override
    public Integer updateBetByType(BetType type, BetValue betValue) {
        if (WORLD_CUP_WINNER.equals(type)) {
            Query query = new Query();
            query.addCriteria(Criteria.where("type").is(type.name()).and("username").is(getLoggedInUser()));
            Update update = new Update();
            update.set("value.winner", betValue.getWinner());

            return toIntExact(mongoTemplate.updateFirst(query, update, Bet.class, BETS.getValue()).getModifiedCount());
        }

        return null;
    }

    @Override
    public Integer updateBetsByType(BetType type, List<BetValue> betValues) {
        if (GROUP_STAGE_PROMOTION.equals(type)) {
            for (BetValue value : betValues) {
                Query query = new Query();
                query.addCriteria(Criteria.where("type").is(type.name()).and("username").is(getLoggedInUser())
                        .and("value.groupName").regex(value.getGroupName()));

                Update update = new Update();
                update.set("value.firstPlace", value.getFirstPlace())
                        .set("value.secondPlace", value.getSecondPlace());

                mongoTemplate.updateFirst(query, update, Bet.class, BETS.getValue()).getModifiedCount();
            }
        }

        return null;
    }

}
