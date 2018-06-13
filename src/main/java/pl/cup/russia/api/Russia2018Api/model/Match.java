package pl.cup.russia.api.Russia2018Api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.cup.russia.api.Russia2018Api.dto.rest.Result;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.util.BetValidator;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;
import static pl.cup.russia.api.Russia2018Api.enums.MatchResult.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "matches")
public class Match {

    @Id
    private String id;

    private Integer leagueApiId;

    private String leagueApiName;

    private Integer matchApiId;

    private LocalDate date;

    private LocalTime time;

    private String status;

    private String hometeamName;

    private Integer hometeamScore;

    private String awayteamName;

    private Integer awayteamScore;

    private Integer hometeamHalftimeScore;

    private Integer awayteamHalftimeScore;

    private Integer hometeamExtraScore;

    private Integer awayteamExtraScore;

    private Integer hometeamPenaltyScore;

    private Integer awayteamPenaltyScore;

    // rest of data if it will be time for it

    public Match(ApiEvent apiEvent) {
        this.leagueApiId = apiEvent.getLeagueId();
        this.leagueApiName = apiEvent.getLeagueName();
        this.matchApiId = apiEvent.getMatchId();
        this.date = apiEvent.getMatchDate();
        this.time = apiEvent.getMatchTime();
        this.status = apiEvent.getMatchStatus();
        this.hometeamName = apiEvent.getMatchHometeamName();
        this.hometeamScore = apiEvent.getMatchHometeamScore();
        this.awayteamName = apiEvent.getMatchAwayteamName();
        this.awayteamScore = apiEvent.getMatchAwayteamScore();
        this.hometeamHalftimeScore = apiEvent.getMatchHometeamHalftimeScore();
        this.awayteamHalftimeScore = apiEvent.getMatchAwayteamHalftimeScore();
        this.hometeamExtraScore = apiEvent.getMatchHometeamExtraScore();
        this.awayteamExtraScore = apiEvent.getMatchAwayteamExtraScore();
        this.hometeamPenaltyScore = apiEvent.getMatchHometeamPenaltyScore();
        this.awayteamPenaltyScore = apiEvent.getMatchAwayteamPenaltyScore();
    }

    public Boolean isExtraTime() {
        if (nonNull(this.hometeamExtraScore) && nonNull(this.awayteamExtraScore))
            return TRUE;
        return FALSE;
    }

    public Boolean isPenalties() {
        if (nonNull(this.hometeamPenaltyScore) && nonNull(this.awayteamPenaltyScore))
            return TRUE;
        return FALSE;
    }

    @JsonProperty(value = "canBetMatch")
    public Boolean canBetMatch() {
        return BetValidator.canBetMatch(this.date, this.time);
    }

    @JsonIgnore // TODO: ignore for now, jackson mapping to repair in this model
    public Result getResult() {
        if (hometeamScore.equals(awayteamScore))
            return new Result(DRAW);

        if (isPenalties())
            return new Result(WIN_PENALTIES, getWinnerName(hometeamPenaltyScore, awayteamPenaltyScore));
        else if (isExtraTime())
            return new Result(WIN_EXTRA_TIME, getWinnerName(hometeamExtraScore, awayteamExtraScore));
        else
            return new Result(WIN_REGULAR_TIME, getWinnerName(hometeamScore, awayteamScore));

    }

    private String getWinnerName(Integer homeScore, Integer awayScore) {
        return (homeScore > awayScore) ? hometeamName : awayteamName;
    }

}
