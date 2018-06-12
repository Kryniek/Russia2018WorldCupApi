package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.model.League;
import pl.cup.russia.api.Russia2018Api.model.Match;
import pl.cup.russia.api.Russia2018Api.repository.MatchRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private FootballApiService apiService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private MatchRepository repository;

    @Override
    public void syncMatches() {
        List<Match> matches = new ArrayList<>();
        List<League> leagues = leagueService.selectLeagues();

        for (League league : leagues) {
            List<Match> leagueMatches = convertApiEventsToMatches(apiService.getApiEventsByLeagueId(league.getLeagueApiId()));
            List<Integer> leagueMatchesIds = leagueMatches.stream().map(match -> match.getMatchApiId()).collect(toList());

            league.setMatchesId(leagueMatchesIds);
            matches.addAll(leagueMatches);
        }

        saveAll(matches);
    }

    @Override
    public List<Match> selectAll() {
        return repository.findAll();
    }

    @Override
    public List<Match> selectMatchesByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    @Override
    public List<Match> saveAll(List<Match> matches) {
        return repository.saveAll(matches);
    }

    private List<Match> convertApiEventsToMatches(List<ApiEvent> apiEvents) {
        return apiEvents.stream().map(evt -> new Match(evt)).collect(toList());
    }

}