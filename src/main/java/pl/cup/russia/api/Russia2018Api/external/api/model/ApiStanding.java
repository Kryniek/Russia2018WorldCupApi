package pl.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;

@Data
public class ApiStanding {

    private String countryName;

    private Integer leagueId;

    private String leagueName;

    private String teamName;

    private Integer overallLeaguePosition;

    private Integer overallLeaguePayed;

    private Integer overallLeagueW;

    private Integer overallLeagueD;

    private Integer overallLeagueL;

    private Integer overallLeagueGF;

    private Integer overallLeagueGA;

    private Integer overallLeaguePTS;

    // other endpoints return also home/away scores but it might not be supported for world cup api
    // and we don't really need it

}
