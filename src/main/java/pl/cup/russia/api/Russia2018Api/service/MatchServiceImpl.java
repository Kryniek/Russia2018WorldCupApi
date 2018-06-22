package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.MATCHES;
import static pl.cup.russia.api.Russia2018Api.enums.MatchStatus.FINAL_TIME;
import static pl.cup.russia.api.Russia2018Api.util.TranslationUtil.translateMatchCountryNameToPolish;
import static pl.cup.russia.api.Russia2018Api.util.TranslationUtil.translateMatchesCountryNamesToPolish;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private FootballApiService apiService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private MatchRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void syncMatches() {
		List<Match> matches = new ArrayList<>();
		List<League> leagues = leagueService.selectLeagues();

		for (League league : leagues) {
			List<Match> leagueMatches = convertApiEventsToMatches(
					apiService.getApiEventsByLeagueId(league.getLeagueApiId()));
			List<Integer> leagueMatchesIds = leagueMatches.stream().map(match -> match.getMatchApiId())
					.collect(toList());

			league.setMatchesId(leagueMatchesIds);
			matches.addAll(leagueMatches);
		}

		saveAll(matches);
	}

	@Override
	public List<Match> selectAll() {
		return repository.findAll();
	}

	/*
	 * TODO
	 * select * from matches IN(...)
	 * 
	 * don't know how to do this :D
	 * I'm little bit confused using mongo repository
	 */
	@Override
	public List<Match> selectByMatchesApiIds(List<Integer> matchesIds) {
		List<Match> matches = matchesIds.stream().map(matchId -> selectByMatchApiId(matchId))
				.collect(Collectors.toList());

		translateMatchesCountryNamesToPolish(matches);

		return matches;
	}

	@Override
	public Match selectByMatchApiId(Integer matchId) {
		Match match = repository.findByMatchApiId(matchId);

		translateMatchCountryNameToPolish(match);

		return match;
	}

	@Override
	public List<Match> selectMatchesByDate(LocalDate date) {
		List<Match> matches = repository.findByDate(date);
		matches.sort(comparing(Match::getTime));

		translateMatchesCountryNamesToPolish(matches);

		return matches;
	}

	@Override
	public List<Match> selectMatchesByDateAndStatus(LocalDate date, String status) {
		return repository.findByDateAndStatus(date, status);
	}

	@Override
	public List<Match> saveAll(List<Match> matches) {
		return repository.saveAll(matches);
	}

	@Override
	public Map<LocalDate, List<Match>> selectAllMatchesByDates() {
		Map<LocalDate, List<Match>> matchesByDates = new TreeMap<>();
		List<Match> matches = selectAll();

		translateMatchesCountryNamesToPolish(matches);

		matches.forEach(match -> {
			matchesByDates.computeIfAbsent(match.getDate(), emptyList -> new ArrayList<>()).add(match);
		});

		matchesByDates.values().forEach(m -> m.sort(comparing(Match::getTime)));

		return matchesByDates;
	}

	private List<Match> convertApiEventsToMatches(List<ApiEvent> apiEvents) {
		return apiEvents.stream().map(evt -> new Match(evt)).collect(toList());
	}

	@Override
	public void updateTodayMatchesResults() {
		List<ApiEvent> apiEvents = apiService.getTodayApiEvents();

		for (ApiEvent event : apiEvents) {
			Query query = new Query();
			query.addCriteria(Criteria.where("matchApiId").is(event.getMatchId()));
			Update update = new Update();
			update.set("hometeamScore", event.getMatchHometeamScore());
			update.set("awayteamScore", event.getMatchAwayteamScore());

			if (event.getMatchStatus().equals(FINAL_TIME.getValue())) {
				update.set("hometeamHalftimeScore", event.getMatchHometeamHalftimeScore());
				update.set("awayteamHalftimeScore", event.getMatchAwayteamHalftimeScore());
				update.set("status", FINAL_TIME.getValue());
			}

			mongoTemplate.updateFirst(query, update, Match.class, MATCHES.getValue());
		}
	}

}
