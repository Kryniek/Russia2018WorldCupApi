package pl.cup.russia.api.Russia2018Api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.cup.russia.api.Russia2018Api.model.Match;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends MongoRepository<Match, String> {

    List<Match> findByDate(LocalDate date);

    List<Match> findByDateAndStatus(LocalDate date, String status);

    Match findByMatchApiId(Integer matchApiId);

}
