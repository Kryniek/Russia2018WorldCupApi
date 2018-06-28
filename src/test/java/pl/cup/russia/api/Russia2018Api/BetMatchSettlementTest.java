package pl.cup.russia.api.Russia2018Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.cup.russia.api.Russia2018Api.dto.BetValue;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.model.Match;
import pl.cup.russia.api.Russia2018Api.service.BetServiceImpl;

import static pl.cup.russia.api.Russia2018Api.constants.BetPointsConstants.*;

public class BetMatchSettlementTest {

    private BetServiceImpl betService;

    @Before
    public void before() {
        betService = new BetServiceImpl();
    }

    @Test
    public void betting_exact_score_should_return_six_points() {
        Match match = getMatch(1, 0);
        Bet bet = getBet(1, 0);

        Bet settledBet = betService.settleBet(match, bet);

        Assert.assertEquals(CORRECT_EXACT_RESULT_PREDICTION, settledBet.getPoints());
    }

    @Test
    public void betting_exact_goal_difference_should_return_three_points() {
        Match match = getMatch(1, 0);
        Bet bet = getBet(2, 1);

        Bet settledBet = betService.settleBet(match, bet);
        Integer expectedPoints = CORRECT_WINNER_PREDICTION + CORRECT_GOAL_DIFFERENCE;

        Assert.assertEquals(expectedPoints, settledBet.getPoints());
    }

    @Test
    public void betting_winner_should_return_two_points() {
        Match match = getMatch(1, 0);
        Bet bet = getBet(2, 0);

        Bet settledBet = betService.settleBet(match, bet);

        Assert.assertEquals(CORRECT_WINNER_PREDICTION, settledBet.getPoints());
    }

    @Test
    public void betting_draw_should_return_three_points() {
        Match match = getMatch(0, 0 );
        Bet bet = getBet(1, 1);

        Bet settledBet = betService.settleBet(match, bet);
        Integer expectedPoints = CORRECT_WINNER_PREDICTION + CORRECT_GOAL_DIFFERENCE;

        Assert.assertEquals(expectedPoints, settledBet.getPoints());
    }

    @Test
    public void betting_bad_winner_and_good_goal_difference_should_return_zero_points() {
        Match match = getMatch(1, 0);
        Bet bet = getBet(0, 1);

        Bet settledBet = betService.settleBet(match, bet);

        Assert.assertEquals(BAD_BET_PREDICTION, settledBet.getPoints());
    }

    private Match getMatch(Integer hometeamScore, Integer awayteamScore) {
        Match match = new Match();
        match.setHometeamName("TEAM A");
        match.setAwayteamName("TEAM B");
        match.setHometeamScore(hometeamScore);
        match.setAwayteamScore(awayteamScore);

        return match;
    }

    private Bet getBet(Integer hometeamScore, Integer awayteamScore) {
        Bet bet = new Bet();
        BetValue betValue = new BetValue();
        betValue.setHometeamScore(hometeamScore);
        betValue.setAwayteamScore(awayteamScore);
        bet.setValue(betValue);

        return bet;
    }

}
