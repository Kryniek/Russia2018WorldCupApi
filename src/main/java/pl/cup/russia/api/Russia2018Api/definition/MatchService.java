package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.model.Match;

import java.time.LocalDate;
import java.util.List;

public interface MatchService {
    void syncMatches();

    List<Match> selectAll();
    
    Match selectByMatchApiId(Integer id);

    List<Match> selectMatchesByDate(LocalDate date);

    List<Match> selectMatchesByDateAndStatus(LocalDate date, String status);

    List<Match> saveAll(List<Match> matches);

    void updateTodayMatchesResults();
}
