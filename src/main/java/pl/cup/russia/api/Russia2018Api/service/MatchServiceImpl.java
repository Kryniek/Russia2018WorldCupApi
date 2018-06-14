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
import static pl.cup.russia.api.Russia2018Api.util.TranslationUtil.translateMatchesCountryNamesToPolish;
import static pl.cup.russia.api.Russia2018Api.util.TranslationUtil.translateMatchCountryNameToPolish;

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

	@Override
	public Match selectById(String id) {
		Match match = repository.findById(id).orElse(null);
		
		translateMatchCountryNameToPolish(match);
		
		return match;
	}

	@Override
	public List<Match> selectMatchesByDate(LocalDate date) {
		List<Match> matches = repository.findByDate(date);

		translateMatchesCountryNamesToPolish(matches);

		return matches;
	}

	@Override
	public List<Match> saveAll(List<Match> matches) {
		return repository.saveAll(matches);
	}

	private List<Match> convertApiEventsToMatches(List<ApiEvent> apiEvents) {
		return apiEvents.stream().map(evt -> new Match(evt)).collect(toList());
	}
}
