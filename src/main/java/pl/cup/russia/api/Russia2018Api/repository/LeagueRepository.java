package pl.cup.russia.api.Russia2018Api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.cup.russia.api.Russia2018Api.model.League;

public interface LeagueRepository extends MongoRepository<League, String> {

}
