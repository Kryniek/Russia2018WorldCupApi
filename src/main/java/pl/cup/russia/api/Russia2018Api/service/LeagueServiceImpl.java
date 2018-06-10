package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiStanding;
import pl.cup.russia.api.Russia2018Api.model.League;
import pl.cup.russia.api.Russia2018Api.model.Match;
import pl.cup.russia.api.Russia2018Api.model.Standing;
import pl.cup.russia.api.Russia2018Api.repository.LeagueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private FootballApiService apiService;

    @Autowired
    private LeagueRepository repository;

    @Override
    public void syncLeagues() {
        List<ApiLeague> apiLeagues = apiService.getApiLeagues();
        List<League> leagues = new ArrayList<>();

        for (ApiLeague apiLeague : apiLeagues) {
            League league = new League(apiLeague);
            if (league.isGroupStage()) {
                List<ApiStanding> apiStandings = apiService.getApiStandingsByLeagueId(league.getLeagueApiId());
                apiStandings.stream().forEach(std -> league.getStandings().add(new Standing(std)));
            }

            List<ApiEvent> apiEvents = apiService.getApiEventsByLeagueId(league.getLeagueApiId());
            apiEvents.stream().forEach(evt -> league.getMatches().add(new Match(evt)));

            leagues.add(league);
        }

        repository.saveAll(leagues);
    }

    @Override
    public List<League> selectLeagues() {
        return repository.findAll();
    }

}
