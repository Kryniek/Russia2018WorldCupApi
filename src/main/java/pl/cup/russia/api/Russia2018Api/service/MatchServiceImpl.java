package pl.cup.russia.api.Russia2018Api.service;

import static java.util.stream.Collectors.toList;
import static pl.cup.russia.api.Russia2018Api.util.TranslationUtil.translateMatchCountryNameToPolish;
import static pl.cup.russia.api.Russia2018Api.util.TranslationUtil.translateMatchesCountryNamesToPolish;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.model.League;
import pl.cup.russia.api.Russia2018Api.model.Match;
import pl.cup.russia.api.Russia2018Api.repository.MatchRepository;

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
	public Map<LocalDate, List<Match>> selectAllMatchesByDates() {
		Map<LocalDate, List<Match>> matchesByDates = new HashMap<>();
		List<Match> matches = selectAll();

		matches.forEach(match -> {
			LocalDate matchDate = match.getDate();

			if (matchesByDates.containsKey(matchDate)) {
				matchesByDates.get(matchDate).add(match);
			} else {
				matchesByDates.put(matchDate, new ArrayList<>(Arrays.asList(match)));
			}
		});
		matchesByDates.values().forEach(v -> v.sort((o1, o2) -> o1.getTime().compareTo(o2.getTime())));

//		matchesByDates.entrySet().stream().sorted(Map.Entry.comparingByKey().reversed())
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, HashMap::new));

		return matchesByDates;
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
