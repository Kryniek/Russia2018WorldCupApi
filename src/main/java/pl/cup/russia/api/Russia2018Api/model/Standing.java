package pl.cup.russia.api.Russia2018Api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiStanding;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Standing {

    private String teamName;

    private Integer position;

    private Integer played;

    private Integer won;

    private Integer draw;

    private Integer lost;

    private Integer goalsScored;

    private Integer goalsLost;

    private Integer points;

    public Standing(ApiStanding apiStanding) {
        this.teamName = apiStanding.getTeamName();
        this.position = apiStanding.getOverallLeaguePosition();
        this.played = apiStanding.getOverallLeaguePayed();
        this.won = apiStanding.getOverallLeagueW();
        this.draw = apiStanding.getOverallLeagueD();
        this.lost = apiStanding.getOverallLeagueL();
        this.goalsScored = apiStanding.getOverallLeagueGF();
        this.goalsLost = apiStanding.getOverallLeagueGA();
        this.points = apiStanding.getOverallLeaguePTS();
    }

}
