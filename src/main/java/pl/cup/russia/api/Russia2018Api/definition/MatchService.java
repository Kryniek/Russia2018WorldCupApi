package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.model.Match;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MatchService {
    void syncMatches();

    List<Match> selectAll();
    
    Map<LocalDate, List<Match>> selectAllMatchesByDates();

    Match selectByMatchApiId(Integer id);

    List<Match> selectMatchesByDate(LocalDate date);

    List<Match> selectMatchesByDateAndStatus(LocalDate date, String status);

    List<Match> saveAll(List<Match> matches);

    void updateTodayMatchesResults();

	List<Match> selectByMatchesApiIds(List<Integer> matchesIds);
}
