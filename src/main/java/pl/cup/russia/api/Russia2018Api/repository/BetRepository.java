package pl.cup.russia.api.Russia2018Api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.cup.russia.api.Russia2018Api.model.Bet;

public interface BetRepository extends MongoRepository<Bet, String> {



}
