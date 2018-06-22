package pl.cup.russia.api.Russia2018Api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.dto.UserBet;
import pl.cup.russia.api.Russia2018Api.enums.BetType;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.model.Match;
import pl.cup.russia.api.Russia2018Api.model.security.User;
import pl.cup.russia.api.Russia2018Api.repository.security.UserRepository;
import pl.cup.russia.api.Russia2018Api.util.exception.security.UserAlreadyExistAuthenticationException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;
import static java.util.Objects.isNull;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.USERS;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BetService betService;

	@Autowired
	private MatchService matchService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User registerNewUserAccount(User user) throws UserAlreadyExistAuthenticationException {
		if (isUserExists(user))
			throw new UserAlreadyExistAuthenticationException(
					"Użytkownik o loginie: " + user.getUsername() + ", już istnieje.");

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPaid(false);

		return repository.save(user);
	}

	@Override
	public User selectUserByUsername(String username) {
		return repository.findUserByUsername(username);
	}

	private Boolean isUserExists(User user) {
		if (!isNull(selectUserByUsername(user.getUsername())))
			return true;

		return false;
	}

	@Override
	public Integer setUserPaidStatusToTrue(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		Update update = new Update();
		update.set("paid", true);

		return toIntExact(mongoTemplate.updateFirst(query, update, User.class, USERS.getValue()).getModifiedCount());
	}

	@Override
	public List<User> getPaidUsers() {
		return getUsersByPaidParam(true);
	}

	@Override
	public List<User> getNonPaidUsers() {
		return getUsersByPaidParam(false);
	}

	public List<User> getUsersByPaidParam(Boolean paid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("paid").is(paid));

		return mongoTemplate.find(query, User.class, USERS.getValue());
	}

	@Override
	public List<UserBet> getUserBets() {
		List<Bet> bets = betService.selectUserBetsByType(BetType.MATCH_RESULT);
		List<Integer> matchesIds = bets.stream().map(bet -> bet.getValue().getMatchId()).collect(Collectors.toList());

		List<Match> matches = matchService.selectByMatchesApiIds(matchesIds);

		List<UserBet> userBets = bets.stream().map(bet -> {
			UserBet userBet = new UserBet();
			userBet.setBet(bet);

			Match match = matches.stream().filter(m -> m.getMatchApiId().equals(bet.getValue().getMatchId()))
					.findFirst().orElse(null);
			userBet.setMatch(match);
			userBet.setDate(match.getDate());
			userBet.setTime(match.getTime());

			return userBet;
		}).collect(Collectors.toList());

		Collections.sort(userBets, Comparator.comparing(UserBet::getDate).thenComparing(UserBet::getTime).reversed());

		return userBets;
	}
}
