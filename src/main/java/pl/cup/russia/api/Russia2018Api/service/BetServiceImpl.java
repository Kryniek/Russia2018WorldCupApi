package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.dto.BetValue;
import pl.cup.russia.api.Russia2018Api.dto.Result;
import pl.cup.russia.api.Russia2018Api.enums.BetType;
import pl.cup.russia.api.Russia2018Api.enums.MatchResult;
import pl.cup.russia.api.Russia2018Api.enums.WinnerSide;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.model.Match;
import pl.cup.russia.api.Russia2018Api.repository.BetRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.lang.Math.toIntExact;
import static pl.cup.russia.api.Russia2018Api.constants.BetPointsConstants.*;
import static pl.cup.russia.api.Russia2018Api.enums.BetStatus.CLOSED;
import static pl.cup.russia.api.Russia2018Api.enums.BetStatus.OPENED;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.GROUP_STAGE_PROMOTION;
import static pl.cup.russia.api.Russia2018Api.enums.BetType.MATCH_RESULT;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.BETS;
import static pl.cup.russia.api.Russia2018Api.enums.MatchStatus.FINAL_TIME;
import static pl.cup.russia.api.Russia2018Api.util.SecurityUtil.getLoggedInUser;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private MatchService matchService;

    @Autowired
    private BetRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Bet createWorldCupWinnerBet(String worldCupWinner) {
        return save(getBet(new BetValue(worldCupWinner), BetType.WORLD_CUP_WINNER));
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
    public List<Bet> selectUserBetsByType(BetType type) {
        return repository.findBetsByTypeAndUsername(type.name(), getLoggedInUser());
    }

    @Override
    public Bet selectUserMatchBet(Integer matchId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(MATCH_RESULT.name()).and("username").is(getLoggedInUser())
                .and("value.matchId").is(matchId));

        return mongoTemplate.findOne(query, Bet.class);
    }

    @Override
    public List<Bet> selectMatchBets(Integer matchId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(MATCH_RESULT.name()).and("value.matchId").is(matchId)
                .and("status").is(OPENED.name()));

        return mongoTemplate.find(query, Bet.class, BETS.getValue());
    }

    @Override
    public Integer updateBetByType(BetType type, BetValue betValue) {
        if (BetType.WORLD_CUP_WINNER.equals(type)) {
            Query query = new Query();
            query.addCriteria(Criteria.where("type").is(type.name()).and("username").is(getLoggedInUser()));
            Update update = new Update();
            update.set("value.winner", betValue.getWinner());

            return toIntExact(mongoTemplate.updateFirst(query, update, Bet.class, BETS.getValue()).getModifiedCount());
        } else if (MATCH_RESULT.equals(type)) {
            Query query = new Query();
            query.addCriteria(Criteria.where("type").is(type.name()).and("username").is(getLoggedInUser())
                    .and("value.matchId").is(betValue.getMatchId()));
            Update update = new Update();
            update.set("value.hometeamScore", betValue.getHometeamScore()).set("value.awayteamScore", betValue.getAwayteamScore());

            return toIntExact(mongoTemplate.updateFirst(query, update, Bet.class, BETS.getValue()).getModifiedCount());
        }

        return valueOf(0);
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

        return valueOf(0);
    }

    @Override
    public void calculatePointsAndUpdateBetRecords() {
        List<Match> todayMatches = matchService.selectMatchesByDateAndStatus(LocalDate.now(), FINAL_TIME.getValue());

        for (Match match : todayMatches) {
            List<Bet> matchBets = selectMatchBets(match.getMatchApiId());

            for (Bet bet : matchBets) {
                settleBet(match, bet);
            }

            updateMatchBetsAfterSettlement(matchBets, match.getMatchApiId());
        }

    }
    
    private void updateMatchBetsAfterSettlement(List<Bet> matchBets, Integer matchId) {
        for (Bet matchBet : matchBets) {
            Query query = new Query();
            query.addCriteria(Criteria.where("type").is(MATCH_RESULT.name()).and("username").is(matchBet.getUsername())
                    .and("value.matchId").is(matchId));
            Update update = new Update();
            update.set("status", CLOSED).set("points", matchBet.getPoints());

            mongoTemplate.updateFirst(query, update, Bet.class, BETS.getValue());
        }
    }

    @Override
    public Bet settleBet(Match match, Bet bet) {
        Result result = match.getResult();
        WinnerSide betWinnerSide = bet.getWinnerSideBasedOnBetValue();
        BetValue betValue = bet.getValue();
        Integer matchGoalDifference = (match.getHometeamScore() - match.getAwayteamScore());

        Integer points = 0;

        if (match.getHometeamScore().equals(betValue.getHometeamScore())
                && match.getAwayteamScore().equals(betValue.getAwayteamScore())) {
            points = CORRECT_EXACT_RESULT_PREDICTION;
        } else {
            WinnerSide resultWinnerSide = result.getSide();

            if (MatchResult.DRAW.equals(result.getResult())) {
                if (WinnerSide.DRAW.equals(betWinnerSide))
                    points = CORRECT_DRAW_RESULT_PREDICTION;
            } else {
                if (resultWinnerSide.equals(betWinnerSide))
                    points = CORRECT_RESULT_PREDICTION;

                if (matchGoalDifference.equals(bet.getBetGoalDifference()))
                    points += CORRECT_GOAL_DIFFERENCE;
            }
        }

        bet.setPoints(points);
        return bet;
    }

}
