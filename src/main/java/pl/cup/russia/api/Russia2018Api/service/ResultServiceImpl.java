package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.ResultService;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.dto.BetResult;

import java.util.List;

import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.BETS;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BetResult> getResultsForPaidUsers() {
        return getResultsForUsers(userService.getPaidUsersUsernames());
    }

    @Override
    public List<BetResult> getResultsForNonPaidUsers() {
        return getResultsForUsers(userService.getNonPaidUsersUsernames());
    }

    public List<BetResult> getResultsForUsers(List<String> usernames) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("username").in(usernames));
        GroupOperation groupOperation = Aggregation.group("username").sum("points").as("totalPoints");
        SortOperation sortOperation = Aggregation.sort(new Sort(Sort.Direction.DESC, "totalPoints"));

        Aggregation aggr = Aggregation.newAggregation(matchOperation, groupOperation, sortOperation);
        AggregationResults<BetResult> betResults = mongoTemplate.aggregate(aggr, BETS.getValue(), BetResult.class);

        return betResults.getMappedResults();
    }

}
