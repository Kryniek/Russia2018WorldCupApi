package pl.cup.russia.api.Russia2018Api.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.cup.russia.api.Russia2018Api.dto.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.BetStatus;
import pl.cup.russia.api.Russia2018Api.enums.BetType;
import pl.cup.russia.api.Russia2018Api.enums.WinnerSide;

import static pl.cup.russia.api.Russia2018Api.enums.WinnerSide.*;

@Data
@Document(collection = "bets")
public class Bet {

    private BetType type;

    private BetStatus status;

    private String username;

    private BetValue value;

    private Integer points;

    public Integer getBetGoalDifference() {
        BetValue betValue = this.getValue();

        return (betValue.getHometeamScore() - betValue.getAwayteamScore());
    }

    public WinnerSide getWinnerSideBasedOnBetValue() {
        BetValue betValue = this.getValue();

        if (betValue.getHometeamScore().equals(betValue.getAwayteamScore()))
            return DRAW;

        if (betValue.getHometeamScore() > betValue.getAwayteamScore())
            return HOME;

        return AWAY;
    }

}
