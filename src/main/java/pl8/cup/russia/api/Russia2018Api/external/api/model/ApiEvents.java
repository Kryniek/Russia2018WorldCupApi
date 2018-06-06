package pl8.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ApiEvents {

    private Integer matchId;

    private Integer countryId;

    private String countryName;

    private Integer leagueId;

    private String leagueName;

    private LocalDate matchDate;

    private String matchStatus; // could be enum

    private LocalTime matchTime;

    private String matchHometeamName;

    private Integer matchHometeamScore;

    private String matchAwayteamName;

    private Integer matchAwayteamScore;

    private Integer matchHometeamHalftimeScore;

    private Integer matchAwayteamHalftimeScore;

    private Integer matchHometeamExtraScore;

    private Integer matchAwayteamExtraScore;

    private Integer matchHometeamPenaltyScore;

    private Integer matchAwayteamPenaltyScore;

    private String matchHometeamSystem; // would love if this could be enum

    private String matchAwayteamSystem;

    private Boolean matchLive;

    private List<ApiScorer> goalscorer;

    private List<ApiCards> cards;

    private ApiLineup lineup;

    private ApiStatistics statistics;

}