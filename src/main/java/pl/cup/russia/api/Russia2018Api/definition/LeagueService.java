package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.model.League;

import java.util.List;
import java.util.Map;

public interface LeagueService {

    void syncLeagues();

    List<League> selectLeagues();

    List<String> selectAllTeams();

    List<String> selectTeamsByLeagueId(Integer leagueId);

    Map<String, List<String>> selectTeamsGroupedByLeagueName();

    List<League> saveAll(List<League> leagues);
}
