package pl.cup.russia.api.Russia2018Api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.model.security.User;
import pl.cup.russia.api.Russia2018Api.repository.security.UserRepository;
import pl.cup.russia.api.Russia2018Api.util.exception.security.UserAlreadyExistAuthenticationException;

import java.util.List;

import static java.lang.Math.toIntExact;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static pl.cup.russia.api.Russia2018Api.enums.DBCollections.USERS;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User registerNewUserAccount(User user) throws UserAlreadyExistAuthenticationException {
        if (isUserExists(user))
            throw new UserAlreadyExistAuthenticationException("Użytkownik o loginie: " + user.getUsername()
                    + ", już istnieje.");

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
    public List<String> getPaidUsersUsernames() {
        return getUsersByPaidParam(true);
    }

    @Override
    public List<String> getNonPaidUsersUsernames() {
        return getUsersByPaidParam(false);
    }

    public List<String> getUsersByPaidParam(Boolean paid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("paid").is(paid));

        List<User> paidUsers = mongoTemplate.find(query, User.class, USERS.getValue());
        return paidUsers.stream().map(User::getUsername).collect(toList());
    }

}
