package pl.cup.russia.api.Russia2018Api.external.api.definition;

import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiStanding;

import java.util.List;

public interface FootballApiService {

	List<ApiLeague> getApiLeagues();

    List<ApiStanding> getApiStandingsByLeagueId(Integer leagueId);

    List<ApiStanding> getApiStandings();

    List<ApiEvent> getApiEventsByLeagueId(Integer leagueId);

    List<ApiEvent> getApiEvents();

    List<ApiEvent> getTodayApiEvents();
}
