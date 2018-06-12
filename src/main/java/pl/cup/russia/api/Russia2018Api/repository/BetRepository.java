package pl.cup.russia.api.Russia2018Api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.cup.russia.api.Russia2018Api.model.Bet;

import java.util.List;

public interface BetRepository extends MongoRepository<Bet, String> {

    Bet findBetByTypeAndUsername(String type, String username);

    List<Bet> findBetsByTypeAndUsername(String type, String username);

}
