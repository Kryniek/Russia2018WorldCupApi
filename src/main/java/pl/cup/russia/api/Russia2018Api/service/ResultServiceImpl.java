package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.ResultService;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.dto.BetResult;
import pl.cup.russia.api.Russia2018Api.model.security.User;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.BETS;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private UserService userService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<BetResult> getResultsForPaidUsers() {
		return getResultsForUsers(userService.getPaidUsers());
	}

	@Override
	public List<BetResult> getResultsForNonPaidUsers() {
		return getResultsForUsers(userService.getNonPaidUsers());
	}

	private List<BetResult> getResultsForUsers(List<User> users) {
		Map<String, User> usersByUsernames = users.stream().collect(toMap(User::getUsername, identity()));

		MatchOperation matchOperation = Aggregation.match(Criteria.where("username").in(usersByUsernames.keySet()));
		GroupOperation groupOperation = Aggregation.group("username").sum("points").as("totalPoints");
		SortOperation sortOperation = Aggregation.sort(new Sort(Sort.Direction.DESC, "totalPoints"));
		Aggregation aggr = Aggregation.newAggregation(matchOperation, groupOperation, sortOperation);

		List<BetResult> betResults = mongoTemplate.aggregate(aggr, BETS.getValue(),
				BetResult.class).getMappedResults();

		betResults.forEach(br -> br.setUser(usersByUsernames.get(br.get_id())));

		return betResults;
	}
}
