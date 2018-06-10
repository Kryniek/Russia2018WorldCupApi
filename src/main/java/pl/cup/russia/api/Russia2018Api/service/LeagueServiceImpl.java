package pl.cup.russia.api.Russia2018Api.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.enums.DBCollections;
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

import static com.mongodb.client.model.Filters.eq;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.LEAGUES;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private FootballApiService apiService;

    @Autowired
    private LeagueRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    @Override
    public List<String> selectAllTeams() {
        return mongoTemplate.getCollection(LEAGUES.getValue())
                .distinct("standings.teamName", String.class).into(new ArrayList<>());
    }

    @Override
    public List<String> selectTeamsByLeagueId(Integer leagueId) {
        return mongoTemplate.getCollection(LEAGUES.getValue())
                .distinct("standings.teamName", eq("leagueApiId", leagueId), String.class)
                .into(new ArrayList<>());

    }

}
